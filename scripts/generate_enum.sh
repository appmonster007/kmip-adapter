#!/bin/bash
# generate_enum.sh
# Refactored generator for KMIP enumerations.
# - Per-file generator functions
# - Flags to enable/disable generation of each artifact
# - Performs a DRY RUN (prints what it would do) when no generation flags provided
# - Compatible with Bash 3.x
set -e

#############################################
# Globals & Defaults
#############################################
BASE_DIR="$(pwd)"
MAIN_JAVA="src/main/java/org/purpleBean/kmip"
TEST_JAVA="src/test/java/org/purpleBean/kmip"
SUB_PATH="common/enumeration"
TEMPLATE_DIR="scripts/templates/enum"

# Generation flags (off by default)
GEN_CLASS=false
GEN_JSON_SER=false
GEN_JSON_DES=false
GEN_XML_SER=false
GEN_XML_DES=false
GEN_TTLV_SER=false
GEN_TTLV_DES=false
GEN_DOMAIN_TEST=false
GEN_JSON_TEST=false
GEN_XML_TEST=false
GEN_TTLV_TEST=false
GEN_BENCHMARK=false

DRY_RUN=false

#############################################
# Helpers
#############################################
usage() {
    cat <<EOF
Usage: $0 [options] <EnumName1> [EnumName2 ...]

If no generation options are provided the script performs a DRY RUN (prints what it would do).
To actually create files pass one or more generation flags.

Options:
  --class         Generate the Enum class
  --json-ser      Generate JSON serializer
  --json-des      Generate JSON deserializer
  --xml-ser       Generate XML serializer
  --xml-des       Generate XML deserializer
  --ttlv-ser      Generate TTLV serializer
  --ttlv-des      Generate TTLV deserializer
  --domain-test   Generate domain/unit test
  --json-test     Generate JSON serialization test
  --xml-test      Generate XML serialization test
  --ttlv-test     Generate TTLV serialization test
  --benchmark     Generate benchmark subject
  --all           Generate everything
  -h, --help      Show this help

Examples:
  # Dry run (no files created)
  $0 State

  # Create only class and json serializer
  $0 --class --json-ser State
EOF
    exit 1
}

# Converts "MyEnum" -> "myEnum"
get_var_name() {
    local name="$1"
    echo "${name:0:1}" | tr '[:upper:]' '[:lower:]'"${name:1}"
}

get_upper_case() {
    to_upper "$@"
}

to_upper() {
    # Read from stdin if no arguments provided
    if [ $# -eq 0 ]; then
        tr '[:lower:]' '[:upper:]'
    else
        # Handle arguments
        local input="$*"
        [ -z "$input" ] && { echo ""; return 1; }
        echo "$input" | tr '[:lower:]' '[:upper:]'
    fi
}
# Converts "MyEnum" -> "MY_ENUM"
to_snake_upper() {
    local name="$1"
    # Use sed and tr; compatible with older bash
    echo "$name" | sed -r 's/([A-Z])/_\1/g' | sed 's/^_//' | tr '[:lower:]' '[:upper:]'
}

pkg_dot() {
    echo "${SUB_PATH//\//.}"
}

# PascalCase from input tokens: "foo_bar" -> "FooBar"
get_pascal_case() {
    local input="$*"
    echo "$input" | sed -E 's/[_-]+/ /g' | awk '{for(i=1;i<=NF;i++){ $i=toupper(substr($i,1,1)) tolower(substr($i,2)) }}1' | tr -d ' '
}

# Run a command or echo dry-run message
do_or_dry_cmd() {
    local message="$1"; shift
    if [ "${DRY_RUN}" = "true" ]; then
        echo "DRY RUN: ${message}"
        return 0
    fi
    "$@"
}

escape_sed_replacement() {
    # escape backslash, ampersand, and delimiter (|)
    echo "$1" | sed -e 's/\\/\\\\/g' -e 's/&/\\\\&/g' -e 's/|/\\\\|/g'
}

render_template() {
    local template_file="$1"
    local out_file="$2"
    shift 2

    if [[ "${DRY_RUN}" == "true" ]]; then
        echo "DRY RUN: would create file: ${out_file} (from ${template_file})"
        return 0
    fi

    mkdir -p "$(dirname "${out_file}")"
    local content
    content="$(cat "${template_file}")"

    while [[ $# -gt 1 ]]; do
        local key="$1"
        local value="$2"
        shift 2
        local esc
        esc="$(escape_sed_replacement "${value}")"
        content="$(printf "%s" "${content}" | sed -e "s|{{${key}}}|${esc}|g")"
    done

    printf "%s" "${content}" > "${out_file}"
}

#############################################
# Filesystem & service helpers (respect DRY_RUN)
#############################################
create_directories() {
    local main_java="$1"
    local test_java="$2"
    local sub_path="$3"

    if [ "${DRY_RUN}" = "true" ]; then
        echo "DRY RUN: would create directories:"
        echo "  ${main_java}/${sub_path}"
        echo "  ${main_java}/codec/json/serializer/kmip/${sub_path}"
        echo "  ${main_java}/codec/json/deserializer/kmip/${sub_path}"
        echo "  ${main_java}/codec/xml/serializer/kmip/${sub_path}"
        echo "  ${main_java}/codec/xml/deserializer/kmip/${sub_path}"
        echo "  ${main_java}/codec/ttlv/serializer/kmip/${sub_path}"
        echo "  ${main_java}/codec/ttlv/deserializer/kmip/${sub_path}"
        echo "  ${test_java}/codec/json/${sub_path}"
        echo "  ${test_java}/codec/xml/${sub_path}"
        echo "  ${test_java}/codec/ttlv/${sub_path}"
        echo "  ${test_java}/benchmark/subjects/${sub_path}"
        echo "  src/main/resources/META-INF/services"
        echo "  src/test/resources/META-INF/services"
        return 0
    fi

    mkdir -p "${main_java}/${sub_path}"
    mkdir -p "${main_java}/codec/json/serializer/kmip/${sub_path}"
    mkdir -p "${main_java}/codec/json/deserializer/kmip/${sub_path}"
    mkdir -p "${main_java}/codec/xml/serializer/kmip/${sub_path}"
    mkdir -p "${main_java}/codec/xml/deserializer/kmip/${sub_path}"
    mkdir -p "${main_java}/codec/ttlv/serializer/kmip/${sub_path}"
    mkdir -p "${main_java}/codec/ttlv/deserializer/kmip/${sub_path}"
    mkdir -p "${test_java}/codec/json/${sub_path}"
    mkdir -p "${test_java}/codec/xml/${sub_path}"
    mkdir -p "${test_java}/codec/ttlv/${sub_path}"
    mkdir -p "${test_java}/benchmark/subjects/${sub_path}"
    mkdir -p "src/main/resources/META-INF/services"
    mkdir -p "src/test/resources/META-INF/services"
}

add_service_entry() {
    local file="$1"
    local entry="$2"

    if [ "${DRY_RUN}" = "true" ]; then
        echo "DRY RUN: would add service entry:"
        echo "  file: ${file}"
        echo "  entry: ${entry}"
        return 0
    fi

    mkdir -p "$(dirname "$file")"
    touch "$file"

    if ! grep -qFx "${entry}" "${file}"; then
        echo "${entry}" >> "${file}"
    fi

    local temp_file="${file}.tmp"
    # portable fallback: some platforms may not support redirecting sort -u's output to same file
    if sort -u "${file}" > "${temp_file}" 2>/dev/null; then
        :
    else
        # fallback attempt (should be identical)
        sort -u "${file}" > "${temp_file}"
    fi

    # remove empty lines
    grep -v '^[[:space:]]*$' "${temp_file}" > "${temp_file}.2" && mv "${temp_file}.2" "${temp_file}"

    if ! cmp -s "${file}" "${temp_file}"; 2>/dev/null; then
        # try to move only when different
        mv "${temp_file}" "${file}"
    else
        rm -f "${temp_file}"
    fi
}

#############################################
# Per-artifact generators (each respects DRY_RUN)
#############################################

generate_enum_class() {
    local ENUM_NAME="$1"
    local ENUM_NAME_SNAKE
    ENUM_NAME_SNAKE="$(to_snake_upper "${ENUM_NAME}")"
    local out_dir="${MAIN_JAVA}/${SUB_PATH}"
    local out_file="${out_dir}/${ENUM_NAME}.java"
    local pdot
    pdot="$(pkg_dot)"

    render_template "${TEMPLATE_DIR}/Enum.java.template" "${out_file}" \
        "pdot" "${pdot}" \
        "ENUM_NAME" "${ENUM_NAME}" \
        "ENUM_NAME_SNAKE" "${ENUM_NAME_SNAKE}"

    echo "Created: ${out_file}"
}

generate_json_serializer() {
    local ENUM_NAME="$1"
    local out_dir="${MAIN_JAVA}/codec/json/serializer/kmip/${SUB_PATH}"
    local out_file="${out_dir}/${ENUM_NAME}JsonSerializer.java"
    local pdot
    pdot="$(pkg_dot)"

    render_template "${TEMPLATE_DIR}/EnumJsonSerializer.java.template" "${out_file}" \
        "pdot" "${pdot}" \
        "ENUM_NAME" "${ENUM_NAME}"

    add_service_entry "src/main/resources/META-INF/services/org.purpleBean.kmip.codec.json.serializer.kmip.KmipDataTypeJsonSerializer" \
        "org.purpleBean.kmip.codec.json.serializer.kmip.${pdot}.${ENUM_NAME}JsonSerializer"

    echo "Created: ${out_file}"
}

generate_json_deserializer() {
    local ENUM_NAME="$1"
    local out_dir="${MAIN_JAVA}/codec/json/deserializer/kmip/${SUB_PATH}"
    local out_file="${out_dir}/${ENUM_NAME}JsonDeserializer.java"
    local pdot
    pdot="$(pkg_dot)"
    local enum_lower
    enum_lower="$(get_var_name "${ENUM_NAME}")"

    render_template "${TEMPLATE_DIR}/EnumJsonDeserializer.java.template" "${out_file}" \
        "pdot" "${pdot}" \
        "ENUM_NAME" "${ENUM_NAME}" \
        "enum_lower" "${enum_lower}"

    add_service_entry "src/main/resources/META-INF/services/org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer" \
        "org.purpleBean.kmip.codec.json.deserializer.kmip.${pdot}.${ENUM_NAME}JsonDeserializer"

    echo "Created: ${out_file}"
}

generate_xml_serializer() {
    local ENUM_NAME="$1"
    local out_dir="${MAIN_JAVA}/codec/xml/serializer/kmip/${SUB_PATH}"
    local out_file="${out_dir}/${ENUM_NAME}XmlSerializer.java"
    local pdot
    pdot="$(pkg_dot)"

    render_template "${TEMPLATE_DIR}/EnumXmlSerializer.java.template" "${out_file}" \
        "pdot" "${pdot}" \
        "ENUM_NAME" "${ENUM_NAME}"

    add_service_entry "src/main/resources/META-INF/services/org.purpleBean.kmip.codec.xml.serializer.kmip.KmipDataTypeXmlSerializer" \
        "org.purpleBean.kmip.codec.xml.serializer.kmip.${pdot}.${ENUM_NAME}XmlSerializer"

    echo "Created: ${out_file}"
}

generate_xml_deserializer() {
    local ENUM_NAME="$1"
    local out_dir="${MAIN_JAVA}/codec/xml/deserializer/kmip/${SUB_PATH}"
    local out_file="${out_dir}/${ENUM_NAME}XmlDeserializer.java"
    local pdot
    pdot="$(pkg_dot)"
    local enum_lower
    enum_lower="$(get_var_name "${ENUM_NAME}")"

    render_template "${TEMPLATE_DIR}/EnumXmlDeserializer.java.template" "${out_file}" \
        "pdot" "${pdot}" \
        "ENUM_NAME" "${ENUM_NAME}" \
        "enum_lower" "${enum_lower}"

    add_service_entry "src/main/resources/META-INF/services/org.purpleBean.kmip.codec.xml.deserializer.kmip.KmipDataTypeXmlDeserializer" \
        "org.purpleBean.kmip.codec.xml.deserializer.kmip.${pdot}.${ENUM_NAME}XmlDeserializer"

    echo "Created: ${out_file}"
}

generate_ttlv_serializer() {
    local ENUM_NAME="$1"
    local out_dir="${MAIN_JAVA}/codec/ttlv/serializer/kmip/${SUB_PATH}"
    local out_file="${out_dir}/${ENUM_NAME}TtlvSerializer.java"
    local pdot
    pdot="$(pkg_dot)"

    render_template "${TEMPLATE_DIR}/EnumTtlvSerializer.java.template" "${out_file}" \
        "pdot" "${pdot}" \
        "ENUM_NAME" "${ENUM_NAME}"

    add_service_entry "src/main/resources/META-INF/services/org.purpleBean.kmip.codec.ttlv.serializer.kmip.KmipDataTypeTtlvSerializer" \
        "org.purpleBean.kmip.codec.ttlv.serializer.kmip.${pdot}.${ENUM_NAME}TtlvSerializer"

    echo "Created: ${out_file}"
}

generate_ttlv_deserializer() {
    local ENUM_NAME="$1"
    local out_dir="${MAIN_JAVA}/codec/ttlv/deserializer/kmip/${SUB_PATH}"
    local out_file="${out_dir}/${ENUM_NAME}TtlvDeserializer.java"
    local pdot
    pdot="$(pkg_dot)"
    local enum_lower
    enum_lower="$(get_var_name "${ENUM_NAME}")"

    render_template "${TEMPLATE_DIR}/EnumTtlvDeserializer.java.template" "${out_file}" \
        "pdot" "${pdot}" \
        "ENUM_NAME" "${ENUM_NAME}" \
        "enum_lower" "${enum_lower}"

    add_service_entry "src/main/resources/META-INF/services/org.purpleBean.kmip.codec.ttlv.deserializer.kmip.KmipDataTypeTtlvDeserializer" \
            "org.purpleBean.kmip.codec.ttlv.deserializer.kmip.${pdot}.${ENUM_NAME}TtlvDeserializer"

    echo "Created: ${out_file}"
}

generate_domain_test() {
    local ENUM_NAME="$1"
    local out_dir="${TEST_JAVA}/${SUB_PATH}"
    local out_file="${out_dir}/${ENUM_NAME}Test.java"
    local pdot
    pdot="$(pkg_dot)"

    render_template "${TEMPLATE_DIR}/EnumTest.java.template" "${out_file}" \
        "pdot" "${pdot}" \
        "ENUM_NAME" "${ENUM_NAME}"

    echo "Created: ${out_file}"
}

generate_serialization_test_for_format() {
    local ENUM_NAME="$1"
    local format="$2"
    local format_upper
    format_upper="$(get_upper_case "${format}")"
    local format_pascal
    format_pascal="$(get_pascal_case "${format}")"
    local suite_name="${ENUM_NAME}${format_pascal}Test"
    local out_dir="${TEST_JAVA}/codec/${format}/${SUB_PATH}"
    local out_file="${out_dir}/${suite_name}.java"
    local pdot
    pdot="$(pkg_dot)"

    render_template "${TEMPLATE_DIR}/EnumCodecTest.java.template" "${out_file}" \
        "pdot" "${pdot}" \
        "ENUM_NAME" "${ENUM_NAME}" \
        "format" "${format}" \
        "format_upper" "${format_upper}" \
        "format_pascal" "${format_pascal}"

    echo "Created: ${out_file}"
}

generate_benchmark_subject() {
    local ENUM_NAME="$1"
    local out_dir="${TEST_JAVA}/benchmark/subjects/${SUB_PATH}"
    local out_file="${out_dir}/${ENUM_NAME}BenchmarkSubject.java"
    local pdot
    pdot="$(pkg_dot)"
    local var_name
    var_name=$(get_var_name "${ENUM_NAME}")

    render_template "${TEMPLATE_DIR}/EnumBenchmarkSubject.java.template" "${out_file}" \
        "pdot" "${pdot}" \
        "ENUM_NAME" "${ENUM_NAME}" \
        "var_name" "${var_name}"

    add_service_entry "src/test/resources/META-INF/services/org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject" \
        "org.purpleBean.kmip.benchmark.subjects.${pdot}.${ENUM_NAME}BenchmarkSubject"

    echo "Created: ${out_file}"
}

#############################################
# Orchestrator for a single enum
#############################################
generate_enum() {
    local ENUM_NAME="$1"
    echo
    echo "Processing enum: ${ENUM_NAME}"

    ${GEN_CLASS} && generate_enum_class "${ENUM_NAME}"
    ${GEN_JSON_SER} && generate_json_serializer "${ENUM_NAME}"
    ${GEN_JSON_DES} && generate_json_deserializer "${ENUM_NAME}"
    ${GEN_XML_SER} && generate_xml_serializer "${ENUM_NAME}"
    ${GEN_XML_DES} && generate_xml_deserializer "${ENUM_NAME}"
    ${GEN_TTLV_SER} && generate_ttlv_serializer "${ENUM_NAME}"
    ${GEN_TTLV_DES} && generate_ttlv_deserializer "${ENUM_NAME}"
    ${GEN_DOMAIN_TEST} && generate_domain_test "${ENUM_NAME}"
    ${GEN_JSON_TEST} && generate_serialization_test_for_format "${ENUM_NAME}" "json"
    ${GEN_XML_TEST} && generate_serialization_test_for_format "${ENUM_NAME}" "xml"
    ${GEN_TTLV_TEST} && generate_serialization_test_for_format "${ENUM_NAME}" "ttlv"
    ${GEN_BENCHMARK} && generate_benchmark_subject "${ENUM_NAME}"

    echo "Finished (or planned) generation for ${ENUM_NAME}"
    echo "Remember to update Standard values in ${ENUM_NAME}.java with real KMIP enum values."
}

#############################################
# Main
#############################################
if [ $# -eq 0 ]; then
    usage
fi

ENUMS=()
any_flag=false

while [ $# -gt 0 ]; do
    case "$1" in
        --class) GEN_CLASS=true; any_flag=true; shift ;;
        --json-ser) GEN_JSON_SER=true; any_flag=true; shift ;;
        --json-des) GEN_JSON_DES=true; any_flag=true; shift ;;
        --xml-ser) GEN_XML_SER=true; any_flag=true; shift ;;
        --xml-des) GEN_XML_DES=true; any_flag=true; shift ;;
        --ttlv-ser) GEN_TTLV_SER=true; any_flag=true; shift ;;
        --ttlv-des) GEN_TTLV_DES=true; any_flag=true; shift ;;
        --domain-test) GEN_DOMAIN_TEST=true; any_flag=true; shift ;;
        --json-test) GEN_JSON_TEST=true; any_flag=true; shift ;;
        --xml-test) GEN_XML_TEST=true; any_flag=true; shift ;;
        --ttlv-test) GEN_TTLV_TEST=true; any_flag=true; shift ;;
        --benchmark) GEN_BENCHMARK=true; any_flag=true; shift ;;
        --all)
            GEN_CLASS=true; GEN_JSON_SER=true; GEN_JSON_DES=true; GEN_XML_SER=true; GEN_XML_DES=true;
            GEN_TTLV_SER=true; GEN_TTLV_DES=true; GEN_DOMAIN_TEST=true; GEN_JSON_TEST=true; GEN_XML_TEST=true;
            GEN_TTLV_TEST=true; GEN_BENCHMARK=true;
            any_flag=true; shift ;;
        -h|--help) usage ;;
        --*) echo "Unknown option: $1"; usage ;;
        *) ENUMS[${#ENUMS[@]}]="$1"; shift ;;
    esac
done

if [ ${#ENUMS[@]} -eq 0 ]; then
    echo "Error: at least one enum name required."
    usage
fi

# If no flags provided -> dry run and plan everything
if [ "${any_flag}" = "false" ]; then
    DRY_RUN=true
    echo "No generation flags provided -> performing DRY RUN (no files will be written)."
    GEN_CLASS=true; GEN_JSON_SER=true; GEN_JSON_DES=true; GEN_XML_SER=true; GEN_XML_DES=true
    GEN_TTLV_SER=true; GEN_TTLV_DES=true; GEN_DOMAIN_TEST=true; GEN_JSON_TEST=true; GEN_XML_TEST=true
    GEN_TTLV_TEST=true; GEN_BENCHMARK=true
fi

# Prepare directories (dry-run will only print)
create_directories "${MAIN_JAVA}" "${TEST_JAVA}" "${SUB_PATH}"

# Process each enum
i=0
while [ "${i}" -lt "${#ENUMS[@]}" ]; do
    enum_name="${ENUMS[$i]}"
    generate_enum "${enum_name}"
    i=$((i + 1))
done

if [ "${DRY_RUN}" = "true" ]; then
    echo
    echo "DRY RUN complete. Nothing was written."
    echo "Re-run with flags (e.g. --all) to actually generate files."
else
    echo
    echo "Generation complete for ${#ENUMS[@]} enum(s)."
    echo "Don't forget to fill in real enum values and review generated TODOs."
fi

exit 0

#!/bin/bash
# generate_datatype.sh
# Refactored generator for KMIP dataTypes.
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
SUB_PATH="common"
TEMPLATE_DIR="scripts/templates/datatype"

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
# Usage / Helpers
#############################################
usage() {
    cat <<EOF
Usage: $0 [options] <Name1> [Name2 ...]

If no generation options are provided the script performs a DRY RUN (prints what it would do).
To actually create files pass one or more generation flags.

Options:
  --class         Generate the  class
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
EOF
    exit 1
}


# 1) Title Case -> PascalCase
#    "Abc Def" -> "AbcDef"
title_to_pascal() {
    local input="$*"
    [ -z "$input" ] && { echo ""; return 1; }

    # replace underscores/hyphens with spaces, normalize whitespace, capitalize each word, then concat
    echo "$input" \
        | sed -E 's/[_-]+/ /g' \
        | awk '{
            for(i=1;i<=NF;i++){
                $i = toupper(substr($i,1,1)) tolower(substr($i,2))
            }
            for(i=1;i<=NF;i++) printf "%s", $i
            print ""
        }'
}

# 2) PascalCase -> Title Case
#    "AbcDef" -> "Abc Def"
pascal_to_title() {
    local input="$*"
    [ -z "$input" ] && { echo ""; return 1; }

    # insert a space between lower-to-upper transitions (e.g. "abcDef" -> "abc Def"),
    # also trim leading/trailing whitespace
    echo "$input" \
        | sed -E 's/([[:lower:][:digit:]])([[:upper:]])/\1 \2/g' \
        | sed -E 's/^[[:space:]]+|[[:space:]]+$//g'
}

# 3) PascalCase -> camelCase
#    "AbcDef" -> "abcDef"
pascal_to_camel() {
    local input="$*"
    [ -z "$input" ] && { echo ""; return 1; }

    local first="${input:0:1}"
    local rest="${input:1}"
    printf "%s%s\n" "$(echo "$first" | tr '[:upper:]' '[:lower:]')" "$rest"
}

# 4) Title Case -> Snake_Case (preserve capitalization of words)
#    "Abc Def" -> "Abc_Def"
title_to_snake() {
    local input
    if [ "$#" -eq 0 ]; then
        # if no arguments, read from stdin
        input="$(cat)"
    else
        input="$*"
    fi
    [ -z "$input" ] && { echo ""; return 1; }

    # collapse whitespace into single underscore, strip leading/trailing underscores
    echo "$input" \
        | sed -E 's/[[:space:]]+/_/g' \
        | sed -E 's/^_|_+$//g'
}

# 5) Snake_Case -> Title Case
#    "Abc_Def" -> "Abc Def"
snake_to_title() {
    local input="$*"
    [ -z "$input" ] && { echo ""; return 1; }

    echo "$input" \
        | sed -E 's/_+/ /g' \
        | awk '{
            for(i=1;i<=NF;i++){
                $i = toupper(substr($i,1,1)) tolower(substr($i,2))
            }
            for(i=1;i<=NF;i++){
                printf "%s%s", (i>1?" ":""), $i
            }
            print ""
        }'
}

# 6) All -> lower case
#    "Abc" -> "abc"
to_lower() {
    local input="$*"
    [ -z "$input" ] && { echo ""; return 1; }
    echo "$input" | tr '[:upper:]' '[:lower:]'
}

# 7) All -> UPPER CASE
#    "Abc" -> "ABC"
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
# -----------------------
# Small wrappers / aliases used by the generator
# -----------------------
# Convert PascalCase -> MY_DATATYPE (snake upper)
to_snake_upper() {
    # canonical approach: PascalCase -> "Pascal Case" -> "Pascal_Case" -> "PASCAL_CASE"
    pascal_to_title "$1" | title_to_snake | to_upper
}

# PascalCase from input tokens (wrapper)
# Accepts "foo_bar", "foo-bar", "Foo Bar", "foo" etc -> "FooBar"
get_pascal_case() {
    title_to_pascal "$*"
}

# LowerCamelCase (myDataType) from any token
get_camel_case() {
    pascal_to_camel "$(get_pascal_case "$@")"
}

# Convenience alias used throughout the old script
get_snake_case() { to_snake_upper "$1"; }

get_var_name() {
    pascal_to_camel "$1"
}

get_upper_case() {
    to_upper "$@"
}

pkg_dot() {
    echo "${SUB_PATH//\//.}"
}

# Simple wrapper to either run command or print dry-run message.
# Usage: do_or_dry "message" command args...
do_or_dry() {
    local msg="$1"
    shift
    if [ "${DRY_RUN}" = "true" ]; then
        echo "DRY RUN: ${msg}"
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
        echo "  ${test_java}/${sub_path}"
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
    mkdir -p "${test_java}/${sub_path}"
    mkdir -p "${test_java}/codec/json/${sub_path}"
    mkdir -p "${test_java}/codec/xml/${sub_path}"
    mkdir -p "${test_java}/codec/ttlv/${sub_path}"
    mkdir -p "${test_java}/benchmark/subjects/${sub_path}"
    mkdir -p "src/main/resources/META-INF/services"
    mkdir -p "src/test/resources/META-INF/services"
}

# Add an implementation class to a service file (unique, sorted, no blank lines)
add_service_entry() {
    local service_file="$1"
    local implementation_class="$2"

    if [ "${DRY_RUN}" = "true" ]; then
        echo "DRY RUN: would add service entry:"
        echo "  file: ${service_file}"
        echo "  entry: ${implementation_class}"
        return 0
    fi

    mkdir -p "$(dirname "${service_file}")"
    touch "${service_file}"

    # append if not present
    if ! grep -qFx "${implementation_class}" "${service_file}"; then
        echo "${implementation_class}" >> "${service_file}"
    fi

    # create a temp file and write sorted unique non-empty lines
    local tmp="${service_file}.tmp.$$"
    sort -u "${service_file}" | grep -v '^[[:space:]]*$' > "${tmp}" || (sort -u "${service_file}" > "${tmp}")
    mv -f "${tmp}" "${service_file}"
}

#############################################
# Per-artifact generators (each respects DRY_RUN)
# Java templates kept mostly as provided; shell logic simplified
#############################################

generate_data_class() {
    local DATA_NAME="$1"
    local DATA_NAME_SNAKE
    DATA_NAME_SNAKE="$(get_snake_case "${DATA_NAME}")"
    local out_dir="${MAIN_JAVA}/${SUB_PATH}"
    local out_file="${out_dir}/${DATA_NAME}.java"
    local pdot
    pdot="$(pkg_dot)"

    render_template "${TEMPLATE_DIR}/DataType.java.template" "${out_file}" \
        "pdot" "${pdot}" \
        "DATA_NAME" "${DATA_NAME}" \
        "DATA_NAME_SNAKE" "${DATA_NAME_SNAKE}"

    echo "Created: ${out_file}"
}

generate_json_serializer() {
    local DATA_NAME="$1"
    local out_dir="${MAIN_JAVA}/codec/json/serializer/kmip/${SUB_PATH}"
    local out_file="${out_dir}/${DATA_NAME}JsonSerializer.java"
    local pdot varname
    pdot="$(pkg_dot)"
    varname="$(get_var_name "${DATA_NAME}")"

    render_template "${TEMPLATE_DIR}/DataTypeJsonSerializer.java.template" "${out_file}" \
        "pdot" "${pdot}" \
        "DATA_NAME" "${DATA_NAME}" \
        "varname" "${varname}"

    add_service_entry "src/main/resources/META-INF/services/org.purpleBean.kmip.codec.json.serializer.kmip.KmipDataTypeJsonSerializer" \
        "org.purpleBean.kmip.codec.json.serializer.kmip.${pdot}.${DATA_NAME}JsonSerializer"

    echo "Created: ${out_file}"
}

generate_json_deserializer() {
    local DATA_NAME="$1"
    local out_dir="${MAIN_JAVA}/codec/json/deserializer/kmip/${SUB_PATH}"
    local out_file="${out_dir}/${DATA_NAME}JsonDeserializer.java"
    local pdot varname data_snake
    pdot="$(pkg_dot)"
    data_snake="$(get_snake_case "${DATA_NAME}")"
    varname="$(get_var_name "${DATA_NAME}")"

    render_template "${TEMPLATE_DIR}/DataTypeJsonDeserializer.java.template" "${out_file}" \
        "pdot" "${pdot}" \
        "DATA_NAME" "${DATA_NAME}" \
        "varname" "${varname}"

    add_service_entry "src/main/resources/META-INF/services/org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer" \
        "org.purpleBean.kmip.codec.json.deserializer.kmip.${pdot}.${DATA_NAME}JsonDeserializer"

    echo "Created: ${out_file}"
}

generate_xml_serializer() {
    local DATA_NAME="$1"
    local out_dir="${MAIN_JAVA}/codec/xml/serializer/kmip/${SUB_PATH}"
    local out_file="${out_dir}/${DATA_NAME}XmlSerializer.java"
    local pdot varname
    pdot="$(pkg_dot)"
    varname="$(get_var_name "${DATA_NAME}")"

    render_template "${TEMPLATE_DIR}/DataTypeXmlSerializer.java.template" "${out_file}" \
        "pdot" "${pdot}" \
        "DATA_NAME" "${DATA_NAME}" \
        "varname" "${varname}"

    add_service_entry "src/main/resources/META-INF/services/org.purpleBean.kmip.codec.xml.serializer.kmip.KmipDataTypeXmlSerializer" \
        "org.purpleBean.kmip.codec.xml.serializer.kmip.${pdot}.${DATA_NAME}XmlSerializer"

    echo "Created: ${out_file}"
}

generate_xml_deserializer() {
    local DATA_NAME="$1"
    local out_dir="${MAIN_JAVA}/codec/xml/deserializer/kmip/${SUB_PATH}"
    local out_file="${out_dir}/${DATA_NAME}XmlDeserializer.java"
    local pdot varname data_snake
    pdot="$(pkg_dot)"
    varname="$(get_var_name "${DATA_NAME}")"
    data_snake="$(get_snake_case "${DATA_NAME}")"

    render_template "${TEMPLATE_DIR}/DataTypeXmlDeserializer.java.template" "${out_file}" \
        "pdot" "${pdot}" \
        "DATA_NAME" "${DATA_NAME}" \
        "varname" "${varname}"

    add_service_entry "src/main/resources/META-INF/services/org.purpleBean.kmip.codec.xml.deserializer.kmip.KmipDataTypeXmlDeserializer" \
        "org.purpleBean.kmip.codec.xml.deserializer.kmip.${pdot}.${DATA_NAME}XmlDeserializer"

    echo "Created: ${out_file}"
}

generate_ttlv_serializer() {
    local DATA_NAME="$1"
    local out_dir="${MAIN_JAVA}/codec/ttlv/serializer/kmip/${SUB_PATH}"
    local out_file="${out_dir}/${DATA_NAME}TtlvSerializer.java"
    local pdot
    pdot="$(pkg_dot)"

    render_template "${TEMPLATE_DIR}/DataTypeTtlvSerializer.java.template" "${out_file}" \
        "pdot" "${pdot}" \
        "DATA_NAME" "${DATA_NAME}"

    add_service_entry "src/main/resources/META-INF/services/org.purpleBean.kmip.codec.ttlv.serializer.kmip.KmipDataTypeTtlvSerializer" \
        "org.purpleBean.kmip.codec.ttlv.serializer.kmip.${pdot}.${DATA_NAME}TtlvSerializer"

    echo "Created: ${out_file}"
}

generate_ttlv_deserializer() {
    local DATA_NAME="$1"
    local out_dir="${MAIN_JAVA}/codec/ttlv/deserializer/kmip/${SUB_PATH}"
    local out_file="${out_dir}/${DATA_NAME}TtlvDeserializer.java"
    local pdot varname data_snake
    pdot="$(pkg_dot)"
    varname="$(get_var_name "${DATA_NAME}")"
    data_snake="$(get_snake_case "${DATA_NAME}")"

    render_template "${TEMPLATE_DIR}/DataTypeTtlvDeserializer.java.template" "${out_file}" \
        "pdot" "${pdot}" \
        "DATA_NAME" "${DATA_NAME}" \
        "varname" "${varname}"

    add_service_entry "src/main/resources/META-INF/services/org.purpleBean.kmip.codec.ttlv.deserializer.kmip.KmipDataTypeTtlvDeserializer" \
        "org.purpleBean.kmip.codec.ttlv.deserializer.kmip.${pdot}.${DATA_NAME}TtlvDeserializer"

    echo "Created: ${out_file}"
}

generate_domain_test() {
    local DATA_NAME="$1"
    local out_dir="${TEST_JAVA}/${SUB_PATH}"
    local out_file="${out_dir}/${DATA_NAME}Test.java"
    local pdot
    pdot="$(pkg_dot)"

    render_template "${TEMPLATE_DIR}/DataTypeTest.java.template" "${out_file}" \
        "pdot" "${pdot}" \
        "DATA_NAME" "${DATA_NAME}"

    echo "Created: ${out_file}"
}

generate_serialization_test_for_format() {
    local DATA_NAME="$1"
    local format="$2"
    local format_upper
    format_upper="$(get_upper_case "${format}")"
    local format_pascal
    format_pascal="$(get_pascal_case "${format}")"
    local suite_name="${DATA_NAME}${format_pascal}Test"
    local out_dir="${TEST_JAVA}/codec/${format}/${SUB_PATH}"
    local out_file="${out_dir}/${suite_name}.java"
    local pdot
    pdot="$(pkg_dot)"

    render_template "${TEMPLATE_DIR}/DataTypeCodecTest.java.template" "${out_file}" \
        "pdot" "${pdot}" \
        "DATA_NAME" "${DATA_NAME}" \
        "format" "${format}" \
        "format_upper" "${format_upper}" \
        "format_pascal" "${format_pascal}" \
        "suite_name" "${suite_name}"

    echo "Created: ${out_file}"
}

generate_benchmark_subject() {
    local DATA_NAME="$1"
    local out_dir="${TEST_JAVA}/benchmark/subjects/${SUB_PATH}"
    local out_file="${out_dir}/${DATA_NAME}BenchmarkSubject.java"
    local pdot
    pdot="$(pkg_dot)"
    local var_name
    var_name=$(echo "${DATA_NAME:0:1}" | tr '[:upper:]' '[:lower:]')${DATA_NAME:1}

    render_template "${TEMPLATE_DIR}/DataTypeBenchmarkSubject.java.template" "${out_file}" \
        "pdot" "${pdot}" \
        "DATA_NAME" "${DATA_NAME}" \
        "var_name" "${var_name}"

    add_service_entry "src/test/resources/META-INF/services/org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject" \
        "org.purpleBean.kmip.benchmark.subjects.${pdot}.${DATA_NAME}BenchmarkSubject"

    echo "Created: ${out_file}"
}

#############################################
# Orchestrator for a single dataType
#############################################
generate_datatype() {
    local DATA_NAME="$1"
    echo
    echo "Processing dataType: ${DATA_NAME}"

    ${GEN_CLASS} && generate_data_class "${DATA_NAME}"
    ${GEN_JSON_SER} && generate_json_serializer "${DATA_NAME}"
    ${GEN_JSON_DES} && generate_json_deserializer "${DATA_NAME}"
    ${GEN_XML_SER} && generate_xml_serializer "${DATA_NAME}"
    ${GEN_XML_DES} && generate_xml_deserializer "${DATA_NAME}"
    ${GEN_TTLV_SER} && generate_ttlv_serializer "${DATA_NAME}"
    ${GEN_TTLV_DES} && generate_ttlv_deserializer "${DATA_NAME}"
    ${GEN_DOMAIN_TEST} && generate_domain_test "${DATA_NAME}"
    ${GEN_JSON_TEST} && generate_serialization_test_for_format "${DATA_NAME}" "json"
    ${GEN_XML_TEST} && generate_serialization_test_for_format "${DATA_NAME}" "xml"
    ${GEN_TTLV_TEST} && generate_serialization_test_for_format "${DATA_NAME}" "ttlv"
    ${GEN_BENCHMARK} && generate_benchmark_subject "${DATA_NAME}"

    echo "Finished (or planned) generation for ${DATA_NAME}"
    echo "Remember to fill in TODOs and validate generated files."
}

#############################################
# Main
#############################################
if [ $# -eq 0 ]; then
    usage
fi

DATATYPES=()
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
        *) DATATYPES[${#DATATYPES[@]}]="$1"; shift ;;
    esac
done

if [ ${#DATATYPES[@]} -eq 0 ]; then
    echo "Error: at least one dataType name required."
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

# Process each dataType
i=0
while [ "${i}" -lt "${#DATATYPES[@]}" ]; do
    data_name="${DATATYPES[$i]}"
    generate_datatype "${data_name}"
    i=$((i + 1))
done

if [ "${DRY_RUN}" = "true" ]; then
    echo
    echo "DRY RUN complete. Nothing was written."
    echo "Re-run with flags (e.g. --all) to actually generate files."
else
    echo
    echo "Generation complete for ${#DATATYPES[@]} dataType(s)."
    echo "Don't forget to fill in TODOs and review generated code."
fi

exit 0

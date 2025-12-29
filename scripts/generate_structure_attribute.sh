#!/bin/bash
# generate_structure_attribute.sh

set -e

#############################################
# Defaults / Globals
#############################################
BASE_DIR="$(pwd)"
MAIN_JAVA="src/main/java/org/purpleBean/kmip"
TEST_JAVA="src/test/java/org/purpleBean/kmip"
SUB_PATH="common/structure"
TEMPLATE_DIR="scripts/templates/structure-attribute"

# Generation flags (default off)
GEN_DOMAIN=false
GEN_TEST=false
GEN_JSON_SER=false
GEN_JSON_DES=false
GEN_XML_SER=false
GEN_XML_DES=false
GEN_TTLV_SER=false
GEN_TTLV_DES=false
GEN_JSON_TEST=false
GEN_XML_TEST=false
GEN_TTLV_TEST=false
GEN_BENCHMARK=false

# Dry run toggled automatically when no flags specified
DRY_RUN=false

#############################################
# Helpers (bash-3-compatible)
#############################################
usage() {
    cat <<EOF
Usage: $0 [options] <StructureName1> [StructureName2 ...]

If no generation options are provided the script performs a DRY RUN (prints what it would do).
To actually write files supply one or more generation flags.

Options:
  --domain        Generate domain class (structure)
  --test          Generate domain unit test
  --json-ser      Generate JSON serializer
  --json-des      Generate JSON deserializer
  --xml-ser       Generate XML serializer
  --xml-des       Generate XML deserializer
  --ttlv-ser      Generate TTLV serializer
  --ttlv-des      Generate TTLV deserializer
  --json-test     Generate JSON serialization test
  --xml-test      Generate XML serialization test
  --ttlv-test     Generate TTLV serialization test
  --benchmark     Generate benchmark subject
  --all           Generate everything
  -h, --help      Show this help

Examples:
  # Dry run (no files will be written)
  $0 CustomAttribute

  # Generate everything for two structures
  $0 --all CustomAttribute SecurityAttribute
EOF
    exit 1
}

# camelCase from PascalCase (or leave if already camelCase)
get_camel_case() {
    local input="$1"
    local first_char="$(echo "${input}" | cut -c1)"
    local lower_first
    lower_first="$(echo "${first_char}" | tr '[:upper:]' '[:lower:]')"
    if [ "${lower_first}" = "${first_char}" ]; then
        echo "${input}"
    else
        echo "${lower_first}${input:1}"
    fi
}

# PascalCase from strings (space/underscore/dash separated)
get_pascal_case() {
    # join all args
    local input="$*"
    # convert separators to spaces, then uppercase first letter of each word and remove spaces
    echo "${input}" | sed -E 's/[_-]/ /g' | awk '{
        for(i=1;i<=NF;i++){
            $i = toupper(substr($i,1,1)) tolower(substr($i,2))
        }
        printf "%s", $1
        for(j=2;j<=NF;j++) printf "%s", $j
    }'
}

# Convert PascalCase -> SNAKE_UPPER
to_snake_upper() {
    local name="$1"
    # insert underscore before each uppercase (except start), then uppercase
    echo "$name" | sed -r 's/([A-Z])/_\1/g' | sed 's/^_//' | tr '[:lower:]' '[:upper:]'
}

# make dotted package path from slash path
slash_to_dot() {
    local s="$1"
    echo "${s//\//.}"
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
# FS & service helpers (respect DRY_RUN)
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

    for dir in serializer deserializer; do
        mkdir -p "${main_java}/codec/json/${dir}/kmip/${sub_path}"
        mkdir -p "${main_java}/codec/xml/${dir}/kmip/${sub_path}"
        mkdir -p "${main_java}/codec/ttlv/${dir}/kmip/${sub_path}"
    done

    mkdir -p "${test_java}/${sub_path}"
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

    # only add exact line if not present
    if ! grep -qFx "${entry}" "${file}"; then
        echo "${entry}" >> "${file}"
        # sort & unique in-place (sort -u -o available on many systems)
        sort -u "${file}" -o "${file}" 2>/dev/null || {
            # fallback if sort -o not supported
            sort -u "${file}" > "${file}.tmp" && mv "${file}.tmp" "${file}"
        }
        echo "Added service entry: ${entry} -> ${file}"
    else
        echo "Service entry already present: ${entry} in ${file}"
    fi
}

#############################################
# Generators (each checks DRY_RUN)
#############################################

generate_domain_class() {
    local class_name="$1"
    local package_path="$2"
    local class_snake
    class_snake=$(to_snake_upper "${class_name}")
    local out_dir="${MAIN_JAVA}/${package_path}"
    local out_file="${out_dir}/${class_name}.java"
    local pkg_dot
    pkg_dot=$(slash_to_dot "${package_path}")

    render_template "${TEMPLATE_DIR}/AttributeStructure.java.template" "${out_file}" \
        "pkg_dot" "${pkg_dot}" \
        "class_name" "${class_name}" \
        "class_snake" "${class_snake}"
}

generate_domain_test() {
    local class_name="$1"
    local package_path="$2"
    local out_dir="${TEST_JAVA}/${package_path}"
    local out_file="${out_dir}/${class_name}Test.java"
    local pkg_dot
    pkg_dot=$(slash_to_dot "${package_path}")

    render_template "${TEMPLATE_DIR}/AttributeStructureTest.java.template" "${out_file}" \
        "pkg_dot" "${pkg_dot}" \
        "class_name" "${class_name}"
}

generate_json_serializer() {
    local class_name="$1"
    local package_path="$2"
    local out_dir="${MAIN_JAVA}/codec/json/serializer/kmip/${package_path}"
    local out_file="${out_dir}/${class_name}JsonSerializer.java"
    local pkg_dot
    pkg_dot=$(slash_to_dot "${package_path}")

    render_template "${TEMPLATE_DIR}/AttributeStructureJsonSerializer.java.template" "${out_file}" \
        "pkg_dot" "${pkg_dot}" \
        "class_name" "${class_name}"

    add_service_entry "src/main/resources/META-INF/services/org.purpleBean.kmip.codec.json.serializer.kmip.KmipDataTypeJsonSerializer" \
        "org.purpleBean.kmip.codec.json.serializer.kmip.${pkg_dot}.${class_name}JsonSerializer"
}

generate_json_deserializer() {
    local class_name="$1"
    local package_path="$2"
    local out_dir="${MAIN_JAVA}/codec/json/deserializer/kmip/${package_path}"
    local out_file="${out_dir}/${class_name}JsonDeserializer.java"
    local pkg_dot
    pkg_dot=$(slash_to_dot "${package_path}")
    local class_lower
    class_lower=$(get_camel_case "${class_name}")

    render_template "${TEMPLATE_DIR}/AttributeStructureJsonDeserializer.java.template" "${out_file}" \
        "pkg_dot" "${pkg_dot}" \
        "class_name" "${class_name}" \
        "class_lower" "${class_lower}"

    add_service_entry "src/main/resources/META-INF/services/org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer" \
        "org.purpleBean.kmip.codec.json.deserializer.kmip.${pkg_dot}.${class_name}JsonDeserializer"
}

generate_xml_serializer() {
    local class_name="$1"
    local package_path="$2"
    local out_dir="${MAIN_JAVA}/codec/xml/serializer/kmip/${package_path}"
    local out_file="${out_dir}/${class_name}XmlSerializer.java"
    local pkg_dot
    pkg_dot=$(slash_to_dot "${package_path}")
    local class_lower
    class_lower=$(get_camel_case "${class_name}")

    render_template "${TEMPLATE_DIR}/AttributeStructureXmlSerializer.java.template" "${out_file}" \
        "pkg_dot" "${pkg_dot}" \
        "class_name" "${class_name}" \
        "class_lower" "${class_lower}"

    add_service_entry "src/main/resources/META-INF/services/org.purpleBean.kmip.codec.xml.serializer.kmip.KmipDataTypeXmlSerializer" \
        "org.purpleBean.kmip.codec.xml.serializer.kmip.${pkg_dot}.${class_name}XmlSerializer"
}

generate_xml_deserializer() {
    local class_name="$1"
    local package_path="$2"
    local out_dir="${MAIN_JAVA}/codec/xml/deserializer/kmip/${package_path}"
    local out_file="${out_dir}/${class_name}XmlDeserializer.java"
    local pkg_dot
    pkg_dot=$(slash_to_dot "${package_path}")
    local class_lower
    class_lower=$(get_camel_case "${class_name}")

    render_template "${TEMPLATE_DIR}/AttributeStructureXmlDeserializer.java.template" "${out_file}" \
        "pkg_dot" "${pkg_dot}" \
        "class_name" "${class_name}" \
        "class_lower" "${class_lower}"

    add_service_entry "src/main/resources/META-INF/services/org.purpleBean.kmip.codec.xml.deserializer.kmip.KmipDataTypeXmlDeserializer" \
        "org.purpleBean.kmip.codec.xml.deserializer.kmip.${pkg_dot}.${class_name}XmlDeserializer"
}

generate_ttlv_serializer() {
    local class_name="$1"
    local package_path="$2"
    local out_dir="${MAIN_JAVA}/codec/ttlv/serializer/kmip/${package_path}"
    local out_file="${out_dir}/${class_name}TtlvSerializer.java"
    local pkg_dot
    pkg_dot=$(slash_to_dot "${package_path}")

    render_template "${TEMPLATE_DIR}/AttributeStructureTtlvSerializer.java.template" "${out_file}" \
        "pkg_dot" "${pkg_dot}" \
        "class_name" "${class_name}"

    add_service_entry "src/main/resources/META-INF/services/org.purpleBean.kmip.codec.ttlv.serializer.kmip.KmipDataTypeTtlvSerializer" \
        "org.purpleBean.kmip.codec.ttlv.serializer.kmip.${pkg_dot}.${class_name}TtlvSerializer"
}

generate_ttlv_deserializer() {
    local class_name="$1"
    local package_path="$2"
    local out_dir="${MAIN_JAVA}/codec/ttlv/deserializer/kmip/${package_path}"
    local out_file="${out_dir}/${class_name}TtlvDeserializer.java"
    local pkg_dot
    pkg_dot=$(slash_to_dot "${package_path}")
    local class_lower
    class_lower=$(get_camel_case "${class_name}")

    render_template "${TEMPLATE_DIR}/AttributeStructureTtlvDeserializer.java.template" "${out_file}" \
        "pkg_dot" "${pkg_dot}" \
        "class_name" "${class_name}" \
        "class_lower" "${class_lower}"

    add_service_entry "src/main/resources/META-INF/services/org.purpleBean.kmip.codec.ttlv.deserializer.kmip.KmipDataTypeTtlvDeserializer" \
        "org.purpleBean.kmip.codec.ttlv.deserializer.kmip.${pkg_dot}.${class_name}TtlvDeserializer"
}

generate_benchmark_subject() {
    local class_name="$1"
    local package_path="$2"
    local out_dir="${TEST_JAVA}/benchmark/subjects/${package_path}"
    local out_file="${out_dir}/${class_name}BenchmarkSubject.java"
    local pkg_dot
    pkg_dot=$(slash_to_dot "${package_path}")
    local var_name
    var_name=$(get_camel_case "${class_name}")

    render_template "${TEMPLATE_DIR}/AttributeStructureBenchmarkSubject.java.template" "${out_file}" \
        "pkg_dot" "${pkg_dot}" \
        "class_name" "${class_name}" \
        "var_name" "${var_name}"

    add_service_entry "src/test/resources/META-INF/services/org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject" \
        "org.purpleBean.kmip.benchmark.subjects.${pkg_dot}.${class_name}BenchmarkSubject"
}

generate_codec_test() {
    local class_name="$1"
    local package_path="$2"
    local format="$3"   # json|xml|ttlv
    local out_dir="${TEST_JAVA}/codec/${format}/${package_path}"
    local format_pascal
    format_pascal=$(get_pascal_case "${format}")
    local suite_name="${class_name}${format_pascal}Test"
    local pkg_dot
    pkg_dot=$(slash_to_dot "${package_path}")

    render_template "${TEMPLATE_DIR}/AttributeStructureCodecTest.java.template" "${out_dir}/${suite_name}.java" \
        "pkg_dot" "${pkg_dot}" \
        "class_name" "${class_name}" \
        "format" "${format}" \
        "format_pascal" "${format_pascal}" \
        "suite_name" "${suite_name}"
}

#############################################
# Orchestrator for one structure
#############################################
generate_attribute_structure() {
    local STRUCTURE_NAME="$1"
    local package_path="$2"   # e.g. common/structure
    local struct_var
    struct_var=$(get_camel_case "${STRUCTURE_NAME}")
    local struct_snake
    struct_snake=$(to_snake_upper "${STRUCTURE_NAME}")
    local pkg_dot
    pkg_dot=$(slash_to_dot "${package_path}")

    printf "\nProcessing %s ...\n" "${STRUCTURE_NAME}"

    ${GEN_DOMAIN} && generate_domain_class "${STRUCTURE_NAME}" "${package_path}"
    ${GEN_TEST} && generate_domain_test "${STRUCTURE_NAME}" "${package_path}"
    ${GEN_JSON_SER} && generate_json_serializer "${STRUCTURE_NAME}" "${package_path}"
    ${GEN_JSON_DES} && generate_json_deserializer "${STRUCTURE_NAME}" "${package_path}"
    ${GEN_XML_SER} && generate_xml_serializer "${STRUCTURE_NAME}" "${package_path}"
    ${GEN_XML_DES} && generate_xml_deserializer "${STRUCTURE_NAME}" "${package_path}"
    ${GEN_TTLV_SER} && generate_ttlv_serializer "${STRUCTURE_NAME}" "${package_path}"
    ${GEN_TTLV_DES} && generate_ttlv_deserializer "${STRUCTURE_NAME}" "${package_path}"
    ${GEN_JSON_TEST} && generate_codec_test "${STRUCTURE_NAME}" "${package_path}" "json"
    ${GEN_XML_TEST} && generate_codec_test "${STRUCTURE_NAME}" "${package_path}" "xml"
    ${GEN_TTLV_TEST} && generate_codec_test "${STRUCTURE_NAME}" "${package_path}" "ttlv"
    ${GEN_BENCHMARK} && generate_benchmark_subject "${STRUCTURE_NAME}" "${package_path}"

    printf "Finished (or planned) generation for %s\n" "${STRUCTURE_NAME}"
    printf "Suggested enum entry to add to KmipTag.Standard:\n"
    printf "    %s(0x%s, \"%s\");\n" "${struct_snake}" "$(printf '%x' $(( (RANDOM % 65000) + 1000 )))" "${STRUCTURE_NAME}"
}

#############################################
# Main
#############################################
# Parse flags and arguments
if [ $# -eq 0 ]; then
    usage
fi

ATTRS=()
any_flag=false

while [ $# -gt 0 ]; do
    case "$1" in
        --domain) GEN_DOMAIN=true; any_flag=true; shift ;;
        --test) GEN_TEST=true; any_flag=true; shift ;;
        --json-ser) GEN_JSON_SER=true; any_flag=true; shift ;;
        --json-des) GEN_JSON_DES=true; any_flag=true; shift ;;
        --xml-ser) GEN_XML_SER=true; any_flag=true; shift ;;
        --xml-des) GEN_XML_DES=true; any_flag=true; shift ;;
        --ttlv-ser) GEN_TTLV_SER=true; any_flag=true; shift ;;
        --ttlv-des) GEN_TTLV_DES=true; any_flag=true; shift ;;
        --json-test) GEN_JSON_TEST=true; any_flag=true; shift ;;
        --xml-test) GEN_XML_TEST=true; any_flag=true; shift ;;
        --ttlv-test) GEN_TTLV_TEST=true; any_flag=true; shift ;;
        --benchmark) GEN_BENCHMARK=true; any_flag=true; shift ;;
        --all)
            GEN_DOMAIN=true; GEN_TEST=true; GEN_JSON_SER=true; GEN_JSON_DES=true;
            GEN_XML_SER=true; GEN_XML_DES=true; GEN_TTLV_SER=true; GEN_TTLV_DES=true;
            GEN_JSON_TEST=true; GEN_XML_TEST=true; GEN_TTLV_TEST=true;
            GEN_BENCHMARK=true;
            any_flag=true; shift ;;
        -h|--help) usage ;;
        --*) echo "Unknown option: $1"; usage ;;
        *) ATTRS[${#ATTRS[@]}]="$1"; shift ;;
    esac
done

if [ ${#ATTRS[@]} -eq 0 ]; then
    echo "Error: at least one structure name required."
    usage
fi

# If no generation flags provided -> dry run (show everything but don't write)
if [ "${any_flag}" = "false" ]; then
    DRY_RUN=true
    echo "No generation flags provided -> performing DRY RUN (no files written)."
    # Plan to show everything in dry run
    GEN_DOMAIN=true; GEN_TEST=true; GEN_JSON_SER=true; GEN_JSON_DES=true
    GEN_XML_SER=true; GEN_XML_DES=true; GEN_TTLV_SER=true; GEN_TTLV_DES=true
    GEN_JSON_TEST=true; GEN_XML_TEST=true; GEN_TTLV_TEST=true
    GEN_BENCHMARK=true
fi

# Create directories once (prints in DRY_RUN)
create_directories "${MAIN_JAVA}" "${TEST_JAVA}" "${SUB_PATH}"

# Iterate structures
for s in "${ATTRS[@]}"; do
    case "${s}" in
        *Attribute) name="${s}" ;;
        *) name="${s}" ;;
    esac
    generate_attribute_structure "${name}" "${SUB_PATH}"
done

if [ "${DRY_RUN}" = "true" ]; then
    echo ""
    echo "DRY RUN complete. Nothing was written. Re-run with flags (e.g. --all) to create files."
else
    echo ""
    echo "Generation complete. Don't forget to:"
    echo "  1. Add the suggested KmipTag.Standard entries"
    echo "  2. Fill in TODOs in generated code"
    echo "  3. Run your tests"
fi

exit 0

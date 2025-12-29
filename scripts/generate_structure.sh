#!/bin/bash
# generate_structure.sh
# Refactored generator for KMIP structures.
# - Per-file generator functions
# - Flags to enable/disable generation of each artifact
# - Performs a DRY RUN (prints what it would do) when no generation flags provided
# - Compatible with Bash 3.x
set -e

# Source the common script
# shellcheck source=common.sh
source "$(dirname "$0")/common.sh"

#############################################
# Globals & Defaults
#############################################
BASE_DIR="$(pwd)"
MAIN_JAVA="src/main/java/org/purpleBean/kmip"
TEST_JAVA="src/test/java/org/purpleBean/kmip"
SUB_PATH="common/structure"
TEMPLATE_DIR="scripts/templates/structure"

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
Usage: $0 [options] <StructureName1> [StructureName2 ...]

If no generation options are provided the script performs a DRY RUN (prints what it would do).
To actually create files pass one or more generation flags.

Options:
  --class         Generate the structure class
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

#############################################
# Per-artifact generators (each respects DRY_RUN)
# Java templates kept mostly as provided; shell logic simplified
#############################################

generate_structure_class() {
    local STRUCTURE_NAME="$1"
    local STRUCTURE_NAME_SNAKE
    STRUCTURE_NAME_SNAKE="$(get_snake_case "${STRUCTURE_NAME}")"
    local out_dir="${MAIN_JAVA}/${SUB_PATH}"
    local out_file="${out_dir}/${STRUCTURE_NAME}.java"
    local pdot
    pdot="$(pkg_dot)"

    render_template "${TEMPLATE_DIR}/Structure.java.template" "${out_file}" \
        "pdot" "${pdot}" \
        "STRUCTURE_NAME" "${STRUCTURE_NAME}" \
        "STRUCTURE_NAME_SNAKE" "${STRUCTURE_NAME_SNAKE}"

    echo "Created: ${out_file}"
}

generate_json_serializer() {
    local STRUCTURE_NAME="$1"
    local out_dir="${MAIN_JAVA}/codec/json/serializer/kmip/${SUB_PATH}"
    local out_file="${out_dir}/${STRUCTURE_NAME}JsonSerializer.java"
    local pdot varname
    pdot="$(pkg_dot)"
    varname="$(get_var_name "${STRUCTURE_NAME}")"

    render_template "${TEMPLATE_DIR}/StructureJsonSerializer.java.template" "${out_file}" \
        "pdot" "${pdot}" \
        "STRUCTURE_NAME" "${STRUCTURE_NAME}" \
        "varname" "${varname}"

    add_service_entry "src/main/resources/META-INF/services/org.purpleBean.kmip.codec.json.serializer.kmip.KmipDataTypeJsonSerializer" \
        "org.purpleBean.kmip.codec.json.serializer.kmip.${pdot}.${STRUCTURE_NAME}JsonSerializer"

    echo "Created: ${out_file}"
}

generate_json_deserializer() {
    local STRUCTURE_NAME="$1"
    local out_dir="${MAIN_JAVA}/codec/json/deserializer/kmip/${SUB_PATH}"
    local out_file="${out_dir}/${STRUCTURE_NAME}JsonDeserializer.java"
    local pdot varname struct_snake
    pdot="$(pkg_dot)"
    struct_snake="$(get_snake_case "${STRUCTURE_NAME}")"
    varname="$(get_var_name "${STRUCTURE_NAME}")"

    render_template "${TEMPLATE_DIR}/StructureJsonDeserializer.java.template" "${out_file}" \
        "pdot" "${pdot}" \
        "STRUCTURE_NAME" "${STRUCTURE_NAME}" \
        "varname" "${varname}"

    add_service_entry "src/main/resources/META-INF/services/org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer" \
        "org.purpleBean.kmip.codec.json.deserializer.kmip.${pdot}.${STRUCTURE_NAME}JsonDeserializer"

    echo "Created: ${out_file}"
}

generate_xml_serializer() {
    local STRUCTURE_NAME="$1"
    local out_dir="${MAIN_JAVA}/codec/xml/serializer/kmip/${SUB_PATH}"
    local out_file="${out_dir}/${STRUCTURE_NAME}XmlSerializer.java"
    local pdot varname
    pdot="$(pkg_dot)"
    varname="$(get_var_name "${STRUCTURE_NAME}")"

    render_template "${TEMPLATE_DIR}/StructureXmlSerializer.java.template" "${out_file}" \
        "pdot" "${pdot}" \
        "STRUCTURE_NAME" "${STRUCTURE_NAME}" \
        "varname" "${varname}"

    add_service_entry "src/main/resources/META-INF/services/org.purpleBean.kmip.codec.xml.serializer.kmip.KmipDataTypeXmlSerializer" \
        "org.purpleBean.kmip.codec.xml.serializer.kmip.${pdot}.${STRUCTURE_NAME}XmlSerializer"

    echo "Created: ${out_file}"
}

generate_xml_deserializer() {
    local STRUCTURE_NAME="$1"
    local out_dir="${MAIN_JAVA}/codec/xml/deserializer/kmip/${SUB_PATH}"
    local out_file="${out_dir}/${STRUCTURE_NAME}XmlDeserializer.java"
    local pdot varname struct_snake
    pdot="$(pkg_dot)"
    varname="$(get_var_name "${STRUCTURE_NAME}")"
    struct_snake="$(get_snake_case "${STRUCTURE_NAME}")"

    render_template "${TEMPLATE_DIR}/StructureXmlDeserializer.java.template" "${out_file}" \
        "pdot" "${pdot}" \
        "STRUCTURE_NAME" "${STRUCTURE_NAME}" \
        "varname" "${varname}"

    add_service_entry "src/main/resources/META-INF/services/org.purpleBean.kmip.codec.xml.deserializer.kmip.KmipDataTypeXmlDeserializer" \
        "org.purpleBean.kmip.codec.xml.deserializer.kmip.${pdot}.${STRUCTURE_NAME}XmlDeserializer"

    echo "Created: ${out_file}"
}

generate_ttlv_serializer() {
    local STRUCTURE_NAME="$1"
    local out_dir="${MAIN_JAVA}/codec/ttlv/serializer/kmip/${SUB_PATH}"
    local out_file="${out_dir}/${STRUCTURE_NAME}TtlvSerializer.java"
    local pdot
    pdot="$(pkg_dot)"

    render_template "${TEMPLATE_DIR}/StructureTtlvSerializer.java.template" "${out_file}" \
        "pdot" "${pdot}" \
        "STRUCTURE_NAME" "${STRUCTURE_NAME}"

    add_service_entry "src/main/resources/META-INF/services/org.purpleBean.kmip.codec.ttlv.serializer.kmip.KmipDataTypeTtlvSerializer" \
        "org.purpleBean.kmip.codec.ttlv.serializer.kmip.${pdot}.${STRUCTURE_NAME}TtlvSerializer"

    echo "Created: ${out_file}"
}

generate_ttlv_deserializer() {
    local STRUCTURE_NAME="$1"
    local out_dir="${MAIN_JAVA}/codec/ttlv/deserializer/kmip/${SUB_PATH}"
    local out_file="${out_dir}/${STRUCTURE_NAME}TtlvDeserializer.java"
    local pdot varname struct_snake
    pdot="$(pkg_dot)"
    varname="$(get_var_name "${STRUCTURE_NAME}")"
    struct_snake="$(get_snake_case "${STRUCTURE_NAME}")"

    render_template "${TEMPLATE_DIR}/StructureTtlvDeserializer.java.template" "${out_file}" \
        "pdot" "${pdot}" \
        "STRUCTURE_NAME" "${STRUCTURE_NAME}" \
        "varname" "${varname}"

    add_service_entry "src/main/resources/META-INF/services/org.purpleBean.kmip.codec.ttlv.deserializer.kmip.KmipDataTypeTtlvDeserializer" \
        "org.purpleBean.kmip.codec.ttlv.deserializer.kmip.${pdot}.${STRUCTURE_NAME}TtlvDeserializer"

    echo "Created: ${out_file}"
}

generate_domain_test() {
    local STRUCTURE_NAME="$1"
    local out_dir="${TEST_JAVA}/${SUB_PATH}"
    local out_file="${out_dir}/${STRUCTURE_NAME}Test.java"
    local pdot
    pdot="$(pkg_dot)"

    render_template "${TEMPLATE_DIR}/StructureTest.java.template" "${out_file}" \
        "pdot" "${pdot}" \
        "STRUCTURE_NAME" "${STRUCTURE_NAME}"

    echo "Created: ${out_file}"
}

generate_serialization_test_for_format() {
    local STRUCTURE_NAME="$1"
    local format="$2"
    local format_upper
    format_upper="$(get_upper_case "${format}")"
    local format_pascal
    format_pascal="$(get_pascal_case "${format}")"
    local suite_name="${STRUCTURE_NAME}${format_pascal}Test"
    local out_dir="${TEST_JAVA}/codec/${format}/${SUB_PATH}"
    local out_file="${out_dir}/${suite_name}.java"
    local pdot
    pdot="$(pkg_dot)"

    render_template "${TEMPLATE_DIR}/StructureCodecTest.java.template" "${out_file}" \
        "pdot" "${pdot}" \
        "STRUCTURE_NAME" "${STRUCTURE_NAME}" \
        "format" "${format}" \
        "format_upper" "${format_upper}" \
        "format_pascal" "${format_pascal}" \
        "suite_name" "${suite_name}"

    echo "Created: ${out_file}"
}

generate_benchmark_subject() {
    local STRUCTURE_NAME="$1"
    local out_dir="${TEST_JAVA}/benchmark/subjects/${SUB_PATH}"
    local out_file="${out_dir}/${STRUCTURE_NAME}BenchmarkSubject.java"
    local pdot
    pdot="$(pkg_dot)"
    local var_name
    var_name=$(echo "${STRUCTURE_NAME:0:1}" | tr '[:upper:]' '[:lower:]')${STRUCTURE_NAME:1}

    render_template "${TEMPLATE_DIR}/StructureBenchmarkSubject.java.template" "${out_file}" \
        "pdot" "${pdot}" \
        "STRUCTURE_NAME" "${STRUCTURE_NAME}" \
        "var_name" "${var_name}"

    add_service_entry "src/test/resources/META-INF/services/org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject" \
        "org.purpleBean.kmip.benchmark.subjects.${pdot}.${STRUCTURE_NAME}BenchmarkSubject"

    echo "Created: ${out_file}"
}

#############################################
# Orchestrator for a single structure
#############################################
generate_structure() {
    local STRUCTURE_NAME="$1"
    echo
    echo "Processing structure: ${STRUCTURE_NAME}"

    ${GEN_CLASS} && generate_structure_class "${STRUCTURE_NAME}"
    ${GEN_JSON_SER} && generate_json_serializer "${STRUCTURE_NAME}"
    ${GEN_JSON_DES} && generate_json_deserializer "${STRUCTURE_NAME}"
    ${GEN_XML_SER} && generate_xml_serializer "${STRUCTURE_NAME}"
    ${GEN_XML_DES} && generate_xml_deserializer "${STRUCTURE_NAME}"
    ${GEN_TTLV_SER} && generate_ttlv_serializer "${STRUCTURE_NAME}"
    ${GEN_TTLV_DES} && generate_ttlv_deserializer "${STRUCTURE_NAME}"
    ${GEN_DOMAIN_TEST} && generate_domain_test "${STRUCTURE_NAME}"
    ${GEN_JSON_TEST} && generate_serialization_test_for_format "${STRUCTURE_NAME}" "json"
    ${GEN_XML_TEST} && generate_serialization_test_for_format "${STRUCTURE_NAME}" "xml"
    ${GEN_TTLV_TEST} && generate_serialization_test_for_format "${STRUCTURE_NAME}" "ttlv"
    ${GEN_BENCHMARK} && generate_benchmark_subject "${STRUCTURE_NAME}"

    echo "Finished (or planned) generation for ${STRUCTURE_NAME}"
    echo "Remember to fill in TODOs and validate generated files."
}

#############################################
# Main
#############################################
if [ $# -eq 0 ]; then
    usage
fi

STRUCTURES=()
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
        *) STRUCTURES[${#STRUCTURES[@]}]="$1"; shift ;;
    esac
done

if [ ${#STRUCTURES[@]} -eq 0 ]; then
    echo "Error: at least one structure name required."
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

# Process each structure
i=0
while [ "${i}" -lt "${#STRUCTURES[@]}" ]; do
    struct_name="${STRUCTURES[$i]}"
    generate_structure "${struct_name}"
    i=$((i + 1))
done

if [ "${DRY_RUN}" = "true" ]; then
    echo
    echo "DRY RUN complete. Nothing was written."
    echo "Re-run with flags (e.g. --all) to actually generate files."
else
    echo
    echo "Generation complete for ${#STRUCTURES[@]} structure(s)."
    echo "Don't forget to fill in TODOs and review generated code."
fi

exit 0

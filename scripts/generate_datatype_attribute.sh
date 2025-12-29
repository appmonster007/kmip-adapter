#!/bin/bash
set -euo pipefail

# Source the common script
# shellcheck source=common.sh
source "$(dirname "$0")/common.sh"

#############################################
# generate_attribute.sh
#
# Generator for KMIP attribute artifacts.
# - Per-artifact generator functions
# - Flags to select which artifacts to generate
# - Dry-run mode when no generation flags provided (prints what would be done)
#############################################

#############################################
# Globals & Defaults
#############################################
BASE_DIR="$(pwd)"
MAIN_JAVA="src/main/java/org/purpleBean/kmip"
TEST_JAVA="src/test/java/org/purpleBean/kmip"
SUB_PATH="common"

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

# Dry run toggled automatically when no flags provided
DRY_RUN=false

TEMPLATE_DIR="scripts/templates/datatype-attribute"

#############################################
# Helpers
#############################################
usage() {
    cat <<EOF
Usage: $0 [options] <Name1> [Name2 ...]

If no generation options are provided the script performs a DRY RUN (prints what it would do).
To actually create files pass one or more generation flags.

Options:
  --class         Generate the attribute class
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
# Generators (each respects DRY_RUN)
#############################################

generate_attribute_class() {
    local ATTRIBUTE_NAME="$1"
    local ATTRIBUTE_NAME_SNAKE="$2"
    local ATTRIBUTE_VAR_NAME="$3"
    local path="${MAIN_JAVA}/${SUB_PATH}/${ATTRIBUTE_NAME}.java"

    local FIELD_TYPE="OffsetDateTime"
    local FIELD_NAME="value"
    local ENCODING_TYPE="DATE_TIME"
    local DEFAULT_VALUE='OffsetDateTime.of(2024, 1, 2, 3, 4, 5, 0, ZoneOffset.UTC)'

    render_template "${TEMPLATE_DIR}/Attribute.java.template" "${path}" \
        "SUB_PATH" "${SUB_PATH}" \
        "ATTRIBUTE_NAME" "${ATTRIBUTE_NAME}" \
        "ATTRIBUTE_NAME_SNAKE" "${ATTRIBUTE_NAME_SNAKE}" \
        "ATTRIBUTE_VAR_NAME" "${ATTRIBUTE_VAR_NAME}" \
        "FIELD_TYPE" "${FIELD_TYPE}" \
        "FIELD_NAME" "${FIELD_NAME}" \
        "ENCODING_TYPE" "${ENCODING_TYPE}" \
        "DEFAULT_VALUE" "${DEFAULT_VALUE}"

    echo "Created: ${path}"
}

generate_json_serializer() {
    local ATTRIBUTE_NAME="$1"
    local path="${MAIN_JAVA}/codec/json/serializer/kmip/${SUB_PATH}/${ATTRIBUTE_NAME}JsonSerializer.java"

    render_template "${TEMPLATE_DIR}/AttributeJsonSerializer.java.template" "${path}" \
        "SUB_PATH" "${SUB_PATH}" \
        "ATTRIBUTE_NAME" "${ATTRIBUTE_NAME}"

    add_service_entry "src/main/resources/META-INF/services/org.purpleBean.kmip.codec.json.serializer.kmip.KmipDataTypeJsonSerializer" \
        "org.purpleBean.kmip.codec.json.serializer.kmip.$(pkg_dot).${ATTRIBUTE_NAME}JsonSerializer"

    echo "Created: ${path}"
}

generate_json_deserializer() {
    local ATTRIBUTE_NAME="$1"
    local ATTRIBUTE_NAME_SNAKE="$2"
    local path="${MAIN_JAVA}/codec/json/deserializer/kmip/${SUB_PATH}/${ATTRIBUTE_NAME}JsonDeserializer.java"

    local FIELD_TYPE="OffsetDateTime"
    local FIELD_NAME="value"
    local ENCODING_TYPE="DATE_TIME"

    render_template "${TEMPLATE_DIR}/AttributeJsonDeserializer.java.template" "${path}" \
        "SUB_PATH" "${SUB_PATH}" \
        "ATTRIBUTE_NAME" "${ATTRIBUTE_NAME}" \
        "ATTRIBUTE_NAME_SNAKE" "${ATTRIBUTE_NAME_SNAKE}" \
        "FIELD_TYPE" "${FIELD_TYPE}" \
        "FIELD_NAME" "${FIELD_NAME}" \
        "ENCODING_TYPE" "${ENCODING_TYPE}"

    add_service_entry "src/main/resources/META-INF/services/org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer" \
        "org.purpleBean.kmip.codec.json.deserializer.kmip.$(pkg_dot).${ATTRIBUTE_NAME}JsonDeserializer"

    echo "Created: ${path}"
}

generate_xml_serializer() {
    local ATTRIBUTE_NAME="$1"
    local path="${MAIN_JAVA}/codec/xml/serializer/kmip/${SUB_PATH}/${ATTRIBUTE_NAME}XmlSerializer.java"

    render_template "${TEMPLATE_DIR}/AttributeXmlSerializer.java.template" "${path}" \
        "SUB_PATH" "${SUB_PATH}" \
        "ATTRIBUTE_NAME" "${ATTRIBUTE_NAME}"

    add_service_entry "src/main/resources/META-INF/services/org.purpleBean.kmip.codec.xml.serializer.kmip.KmipDataTypeXmlSerializer" \
        "org.purpleBean.kmip.codec.xml.serializer.kmip.$(pkg_dot).${ATTRIBUTE_NAME}XmlSerializer"

    echo "Created: ${path}"
}

generate_xml_deserializer() {
    local ATTRIBUTE_NAME="$1"
    local ATTRIBUTE_NAME_SNAKE="$2"
    local path="${MAIN_JAVA}/codec/xml/deserializer/kmip/${SUB_PATH}/${ATTRIBUTE_NAME}XmlDeserializer.java"

    local FIELD_TYPE="OffsetDateTime"
    local FIELD_NAME="value"
    local ENCODING_TYPE="DATE_TIME"

    render_template "${TEMPLATE_DIR}/AttributeXmlDeserializer.java.template" "${path}" \
        "SUB_PATH" "${SUB_PATH}" \
        "ATTRIBUTE_NAME" "${ATTRIBUTE_NAME}" \
        "ATTRIBUTE_NAME_SNAKE" "${ATTRIBUTE_NAME_SNAKE}" \
        "FIELD_TYPE" "${FIELD_TYPE}" \
        "FIELD_NAME" "${FIELD_NAME}" \
        "ENCODING_TYPE" "${ENCODING_TYPE}"

    add_service_entry "src/main/resources/META-INF/services/org.purpleBean.kmip.codec.xml.deserializer.kmip.KmipDataTypeXmlDeserializer" \
        "org.purpleBean.kmip.codec.xml.deserializer.kmip.$(pkg_dot).${ATTRIBUTE_NAME}XmlDeserializer"

    echo "Created: ${path}"
}

generate_ttlv_serializer() {
    local ATTRIBUTE_NAME="$1"
    local path="${MAIN_JAVA}/codec/ttlv/serializer/kmip/${SUB_PATH}/${ATTRIBUTE_NAME}TtlvSerializer.java"

    render_template "${TEMPLATE_DIR}/AttributeTtlvSerializer.java.template" "${path}" \
        "SUB_PATH" "${SUB_PATH}" \
        "ATTRIBUTE_NAME" "${ATTRIBUTE_NAME}"

    add_service_entry "src/main/resources/META-INF/services/org.purpleBean.kmip.codec.ttlv.serializer.kmip.KmipDataTypeTtlvSerializer" \
        "org.purpleBean.kmip.codec.ttlv.serializer.kmip.$(pkg_dot).${ATTRIBUTE_NAME}TtlvSerializer"

    echo "Created: ${path}"
}

generate_ttlv_deserializer() {
    local ATTRIBUTE_NAME="$1"
    local ATTRIBUTE_NAME_SNAKE="$2"
    local path="${MAIN_JAVA}/codec/ttlv/deserializer/kmip/${SUB_PATH}/${ATTRIBUTE_NAME}TtlvDeserializer.java"

    local FIELD_TYPE="OffsetDateTime"
    local FIELD_NAME="value"
    local ENCODING_TYPE="DATE_TIME"

    render_template "${TEMPLATE_DIR}/AttributeTtlvDeserializer.java.template" "${path}" \
        "SUB_PATH" "${SUB_PATH}" \
        "ATTRIBUTE_NAME" "${ATTRIBUTE_NAME}" \
        "ATTRIBUTE_NAME_SNAKE" "${ATTRIBUTE_NAME_SNAKE}" \
        "FIELD_TYPE" "${FIELD_TYPE}" \
        "FIELD_NAME" "${FIELD_NAME}" \
        "ENCODING_TYPE" "${ENCODING_TYPE}"

    add_service_entry "src/main/resources/META-INF/services/org.purpleBean.kmip.codec.ttlv.deserializer.kmip.KmipDataTypeTtlvDeserializer" \
        "org.purpleBean.kmip.codec.ttlv.deserializer.kmip.$(pkg_dot).${ATTRIBUTE_NAME}TtlvDeserializer"

    echo "Created: ${path}"
}

generate_domain_test() {
    local ATTRIBUTE_NAME="$1"
    local path="${TEST_JAVA}/${SUB_PATH}/${ATTRIBUTE_NAME}Test.java"

    local FIELD_TYPE="OffsetDateTime"
    local FIELD_NAME="value"
    local ENCODING_TYPE="DATE_TIME"
    local DEFAULT_VALUE='OffsetDateTime.of(2024, 1, 2, 3, 4, 5, 0, ZoneOffset.UTC)'

    render_template "${TEMPLATE_DIR}/AttributeTest.java.template" "${path}" \
        "SUB_PATH" "${SUB_PATH}" \
        "ATTRIBUTE_NAME" "${ATTRIBUTE_NAME}" \
        "FIELD_TYPE" "${FIELD_TYPE}" \
        "FIELD_NAME" "${FIELD_NAME}" \
        "ENCODING_TYPE" "${ENCODING_TYPE}" \
        "DEFAULT_VALUE" "${DEFAULT_VALUE}"

    echo "Created: ${path}"
}

generate_codec_test() {
    local ATTRIBUTE_NAME="$1"
    local format="$2"
    local codec_pascal
    codec_pascal="$(get_pascal_case "${format}")"
    local path="${TEST_JAVA}/codec/${format}/${SUB_PATH}/${ATTRIBUTE_NAME}${codec_pascal}Test.java"

    local FIELD_TYPE="OffsetDateTime"
    local FIELD_NAME="value"
    local DEFAULT_VALUE='OffsetDateTime.of(2024, 1, 2, 3, 4, 5, 0, ZoneOffset.UTC)'

    render_template "${TEMPLATE_DIR}/AttributeCodecTest.java.template" "${path}" \
        "SUB_PATH" "${SUB_PATH}" \
        "ATTRIBUTE_NAME" "${ATTRIBUTE_NAME}" \
        "FIELD_TYPE" "${FIELD_TYPE}" \
        "FIELD_NAME" "${FIELD_NAME}" \
        "DEFAULT_VALUE" "${DEFAULT_VALUE}" \
        "CODEC_LOWER" "${format}" \
        "CODEC_PASCAL" "${codec_pascal}"

    echo "Created: ${path}"
}

generate_benchmark_subject() {
    local ATTRIBUTE_NAME="$1"
    local ATTRIBUTE_VAR_NAME
    ATTRIBUTE_VAR_NAME="$(get_var_name "${ATTRIBUTE_NAME}")"
    local path="${TEST_JAVA}/benchmark/subjects/${SUB_PATH}/${ATTRIBUTE_NAME}BenchmarkSubject.java"

    local FIELD_TYPE="OffsetDateTime"
    local FIELD_NAME="value"
    local DEFAULT_VALUE='OffsetDateTime.of(2024, 1, 2, 3, 4, 5, 0, ZoneOffset.UTC)'

    render_template "${TEMPLATE_DIR}/AttributeBenchmarkSubject.java.template" "${path}" \
        "SUB_PATH" "${SUB_PATH}" \
        "ATTRIBUTE_NAME" "${ATTRIBUTE_NAME}" \
        "ATTRIBUTE_VAR_NAME" "${ATTRIBUTE_VAR_NAME}" \
        "FIELD_TYPE" "${FIELD_TYPE}" \
        "FIELD_NAME" "${FIELD_NAME}" \
        "DEFAULT_VALUE" "${DEFAULT_VALUE}"

    add_service_entry "src/test/resources/META-INF/services/org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject" \
        "org.purpleBean.kmip.benchmark.subjects.$(pkg_dot).${ATTRIBUTE_NAME}BenchmarkSubject"

    echo "Created: ${path}"
}

#############################################
# Orchestrator for one attribute
#############################################
generate_attribute() {
    local ATTRIBUTE_NAME="$1"
    local ATTRIBUTE_NAME_SNAKE
    ATTRIBUTE_NAME_SNAKE="$(to_snake_upper "${ATTRIBUTE_NAME}")"
    local ATTRIBUTE_VAR_NAME
    ATTRIBUTE_VAR_NAME="$(get_var_name "${ATTRIBUTE_NAME}")"

    echo -e "\nProcessing ${ATTRIBUTE_NAME}..."

    $GEN_CLASS        && generate_attribute_class "${ATTRIBUTE_NAME}" "${ATTRIBUTE_NAME_SNAKE}" "${ATTRIBUTE_VAR_NAME}"
    $GEN_JSON_SER     && generate_json_serializer "${ATTRIBUTE_NAME}" "${ATTRIBUTE_NAME_SNAKE}"
    $GEN_JSON_DES     && generate_json_deserializer "${ATTRIBUTE_NAME}" "${ATTRIBUTE_NAME_SNAKE}"
    $GEN_XML_SER      && generate_xml_serializer "${ATTRIBUTE_NAME}" "${ATTRIBUTE_NAME_SNAKE}"
    $GEN_XML_DES      && generate_xml_deserializer "${ATTRIBUTE_NAME}" "${ATTRIBUTE_NAME_SNAKE}"
    $GEN_TTLV_SER     && generate_ttlv_serializer "${ATTRIBUTE_NAME}" "${ATTRIBUTE_NAME_SNAKE}"
    $GEN_TTLV_DES     && generate_ttlv_deserializer "${ATTRIBUTE_NAME}" "${ATTRIBUTE_NAME_SNAKE}"
    $GEN_DOMAIN_TEST  && generate_domain_test "${ATTRIBUTE_NAME}"
    $GEN_JSON_TEST    && generate_codec_test "${ATTRIBUTE_NAME}" "json"
    $GEN_XML_TEST     && generate_codec_test "${ATTRIBUTE_NAME}" "xml"
    $GEN_TTLV_TEST    && generate_codec_test "${ATTRIBUTE_NAME}" "ttlv"
    $GEN_BENCHMARK    && generate_benchmark_subject "${ATTRIBUTE_NAME}"

    echo "Finished (or planned) generation for ${ATTRIBUTE_NAME}."
    echo "Suggested enum entry to add to KmipTag.Standard:"
    echo "    ${ATTRIBUTE_NAME_SNAKE}(0x\$(printf '%x' \$((RANDOM * 1000 % 65000 + 1000))), \"${ATTRIBUTE_NAME}\");"
}

#############################################
# Main
#############################################
main() {
    if [[ $# -eq 0 ]]; then
        usage
    fi

    local attrs=()
    local any_flag=false

    # Parse flags
    while [[ $# -gt 0 ]]; do
        case "$1" in
            --class) GEN_CLASS=true; any_flag=true ;;
            --json-ser) GEN_JSON_SER=true; any_flag=true ;;
            --json-des) GEN_JSON_DES=true; any_flag=true ;;
            --xml-ser) GEN_XML_SER=true; any_flag=true ;;
            --xml-des) GEN_XML_DES=true; any_flag=true ;;
            --ttlv-ser) GEN_TTLV_SER=true; any_flag=true ;;
            --ttlv-des) GEN_TTLV_DES=true; any_flag=true ;;
            --domain-test) GEN_DOMAIN_TEST=true; any_flag=true ;;
            --json-test|--test-json) GEN_JSON_TEST=true; any_flag=true ;;
            --xml-test|--test-xml) GEN_XML_TEST=true; any_flag=true ;;
            --ttlv-test|--test-ttlv) GEN_TTLV_TEST=true; any_flag=true ;;
            --benchmark) GEN_BENCHMARK=true; any_flag=true ;;
            --all)
                GEN_CLASS=true; GEN_JSON_SER=true; GEN_JSON_DES=true; GEN_XML_SER=true; GEN_XML_DES=true;
                GEN_TTLV_SER=true; GEN_TTLV_DES=true; GEN_DOMAIN_TEST=true; GEN_JSON_TEST=true; GEN_XML_TEST=true; GEN_TTLV_TEST=true;
                GEN_BENCHMARK=true; any_flag=true
                ;;
            --help|-h) usage ;;
            --*) echo "Unknown option: $1"; usage ;;
            *) attrs+=("$1") ;;
        esac
        shift
    done

    if [[ ${#attrs[@]} -eq 0 ]]; then
        echo "Error: at least one attribute name required."
        usage
    fi

    # If no generation flags provided -> dry run (print what would be done)
    if [[ $any_flag == false ]]; then
        DRY_RUN=true
        echo "No generation flags provided -> performing DRY RUN (no files will be written)."
        # Plan to show everything in the dry run
        GEN_CLASS=true; GEN_JSON_SER=true; GEN_JSON_DES=true; GEN_XML_SER=true; GEN_XML_DES=true
        GEN_TTLV_SER=true; GEN_TTLV_DES=true; GEN_DOMAIN_TEST=true; GEN_JSON_TEST=true; GEN_XML_TEST=true; GEN_TTLV_TEST=true
        GEN_BENCHMARK=true
    fi

    # Create directories (in dry-run this will only print)
    create_directories "${MAIN_JAVA}" "${TEST_JAVA}" "${SUB_PATH}"

    # Process each attribute
    for ATTRIBUTE_NAME in "${attrs[@]}"; do
        generate_attribute "${ATTRIBUTE_NAME}"
    done

    if [[ "${DRY_RUN}" == "true" ]]; then
        echo -e "\nDRY RUN complete. Nothing was written."
        echo "Rerun with flags to actually generate files. Example:"
        echo "  $0 --all ActivationDate"
    else
        echo -e "\nGeneration complete! Don't forget to:"
        echo "1. Add the attribute tag to the KmipTag.Standard enum"
        echo "2. Update any relevant documentation"
        echo "3. Run the tests to verify everything works as expected"
    fi
}

main "$@"

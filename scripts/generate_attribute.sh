#!/bin/bash
set -euo pipefail

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

#############################################
# Directory Management (respects DRY_RUN)
#############################################
create_directories() {
    local main_java="$1"
    local test_java="$2"
    local sub_path="$3"

    if [[ "${DRY_RUN}" == "true" ]]; then
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

#############################################
# Service registration helper (respects DRY_RUN)
#############################################
add_service_entry() {
    local service_file="$1"
    local implementation_class="$2"

    if [[ "${DRY_RUN}" == "true" ]]; then
        echo "DRY RUN: would add service entry:"
        echo "  file: ${service_file}"
        echo "  entry: ${implementation_class}"
        return 0
    fi

    mkdir -p "$(dirname "${service_file}")"
    touch "${service_file}"

    if ! grep -qFx "${implementation_class}" "${service_file}"; then
        echo "${implementation_class}" >> "${service_file}"
    fi

    local tmp="${service_file}.tmp.$$"
    sort -u "${service_file}" | grep -v '^[[:space:]]*$' > "${tmp}" || (sort -u "${service_file}" > "${tmp}")
    mv -f "${tmp}" "${service_file}"
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

generate_json_test() {
    local ATTRIBUTE_NAME="$1"
    local path="${TEST_JAVA}/codec/json/${SUB_PATH}/${ATTRIBUTE_NAME}JsonTest.java"

    local FIELD_TYPE="OffsetDateTime"
    local FIELD_NAME="value"
    local DEFAULT_VALUE='OffsetDateTime.of(2024, 1, 2, 3, 4, 5, 0, ZoneOffset.UTC)'

    render_template "${TEMPLATE_DIR}/AttributeJsonTest.java.template" "${path}" \
        "SUB_PATH" "${SUB_PATH}" \
        "ATTRIBUTE_NAME" "${ATTRIBUTE_NAME}" \
        "FIELD_TYPE" "${FIELD_TYPE}" \
        "FIELD_NAME" "${FIELD_NAME}" \
        "DEFAULT_VALUE" "${DEFAULT_VALUE}"

    echo "Created: ${path}"
}

generate_xml_test() {
    local ATTRIBUTE_NAME="$1"
    local path="${TEST_JAVA}/codec/xml/${SUB_PATH}/${ATTRIBUTE_NAME}XmlTest.java"

    local FIELD_TYPE="OffsetDateTime"
    local FIELD_NAME="value"
    local DEFAULT_VALUE='OffsetDateTime.of(2024, 1, 2, 3, 4, 5, 0, ZoneOffset.UTC)'

    render_template "${TEMPLATE_DIR}/AttributeXmlTest.java.template" "${path}" \
        "SUB_PATH" "${SUB_PATH}" \
        "ATTRIBUTE_NAME" "${ATTRIBUTE_NAME}" \
        "FIELD_TYPE" "${FIELD_TYPE}" \
        "FIELD_NAME" "${FIELD_NAME}" \
        "DEFAULT_VALUE" "${DEFAULT_VALUE}"

    echo "Created: ${path}"
}

generate_ttlv_test() {
    local ATTRIBUTE_NAME="$1"
    local path="${TEST_JAVA}/codec/ttlv/${SUB_PATH}/${ATTRIBUTE_NAME}TtlvTest.java"

    local FIELD_TYPE="OffsetDateTime"
    local FIELD_NAME="value"
    local DEFAULT_VALUE='OffsetDateTime.of(2024, 1, 2, 3, 4, 5, 0, ZoneOffset.UTC)'

    render_template "${TEMPLATE_DIR}/AttributeTtlvTest.java.template" "${path}" \
        "SUB_PATH" "${SUB_PATH}" \
        "ATTRIBUTE_NAME" "${ATTRIBUTE_NAME}" \
        "FIELD_TYPE" "${FIELD_TYPE}" \
        "FIELD_NAME" "${FIELD_NAME}" \
        "DEFAULT_VALUE" "${DEFAULT_VALUE}"

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
    $GEN_JSON_TEST    && generate_json_test "${ATTRIBUTE_NAME}"
    $GEN_XML_TEST     && generate_xml_test "${ATTRIBUTE_NAME}"
    $GEN_TTLV_TEST    && generate_ttlv_test "${ATTRIBUTE_NAME}"
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

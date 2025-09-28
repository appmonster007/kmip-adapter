#!/bin/bash
# generate_structure.sh
# Refactored generator for KMIP structures.
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
SUB_PATH="common/structure"

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
        | sed -E 's/^_+|_+$//g'
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
# Convert PascalCase -> MY_STRUCTURE (snake upper)
to_snake_upper() {
    # canonical approach: PascalCase -> "Pascal Case" -> "Pascal_Case" -> "PASCAL_CASE"
    pascal_to_title "$1" | title_to_snake | to_upper
}

# PascalCase from input tokens (wrapper)
# Accepts "foo_bar", "foo-bar", "Foo Bar", "foo" etc -> "FooBar"
get_pascal_case() {
    title_to_pascal "$*"
}

# LowerCamelCase (myStructure) from any token
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

generate_structure_class() {
    local STRUCTURE_NAME="$1"
    local STRUCTURE_NAME_SNAKE
    STRUCTURE_NAME_SNAKE="$(get_snake_case "${STRUCTURE_NAME}")"
    local out_dir="${MAIN_JAVA}/${SUB_PATH}"
    local out_file="${out_dir}/${STRUCTURE_NAME}.java"
    local pdot
    pdot="$(pkg_dot)"

    if [ "${DRY_RUN}" = "true" ]; then
        echo "DRY RUN: would create structure class: ${out_file}"
        return 0
    fi

    mkdir -p "${out_dir}"

    cat > "${out_file}" <<EOF
package org.purpleBean.kmip.${pdot};

import lombok.*;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.common.*;
import org.purpleBean.kmip.common.enumeration.*;
import org.purpleBean.kmip.common.structure.*;
import org.purpleBean.kmip.KmipStructure;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * KMIP ${STRUCTURE_NAME} structure.
 */
@Data
@Builder
public class ${STRUCTURE_NAME} implements KmipStructure {

    public static final KmipTag kmipTag = new KmipTag(KmipTag.Standard.${STRUCTURE_NAME_SNAKE});
    public static final EncodingType encodingType = EncodingType.STRUCTURE;
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2);

    // TODO: Add your structure fields here
    // Example:
    @NonNull
    private final ActivationDate activationDate;
    private final State state;

    @Override
    public KmipTag getKmipTag() {
        return kmipTag;
    }

    @Override
    public EncodingType getEncodingType() {
        return encodingType;
    }

    @Override
    public List<KmipDataType> getValues() {
        List<KmipDataType> values = new ArrayList<>();
        values.add(activationDate);
        values.add(state);
        return values;
    }

    @Override
    public boolean isSupportedFor(@NonNull KmipSpec spec) {
        return supportedVersions.contains(spec);
    }

    public static class ${STRUCTURE_NAME}Builder {
        public ${STRUCTURE_NAME} build() {
            // Validate required fields
            validate();
            return new ${STRUCTURE_NAME}(activationDate, state);
        }

        private void validate() {
            List<KmipDataType> fields = new ArrayList<>();
            fields.add(activationDate);
            fields.add(state);

            // Validate KMIP spec compatibility
            KmipSpec spec = KmipContext.getSpec();
            for (KmipDataType field : fields) {
                if (field != null && !field.isSupportedFor(spec)) {
                    throw new IllegalArgumentException(
                        String.format("%s is not supported for KMIP spec %s", field.getKmipTag().getDescription(), spec)
                    );
                }
            }

            // Validate required fields
            // Add required-field checks as needed
        }
    }
}
EOF

    echo "Created: ${out_file}"
}

generate_json_serializer() {
    local STRUCTURE_NAME="$1"
    local out_dir="${MAIN_JAVA}/codec/json/serializer/kmip/${SUB_PATH}"
    local out_file="${out_dir}/${STRUCTURE_NAME}JsonSerializer.java"
    local pdot varname
    pdot="$(pkg_dot)"
    varname="$(get_var_name "${STRUCTURE_NAME}")"

    if [ "${DRY_RUN}" = "true" ]; then
        echo "DRY RUN: would create JSON serializer: ${out_file}"
        return 0
    fi

    mkdir -p "${out_dir}"

    cat > "${out_file}" <<EOF
package org.purpleBean.kmip.codec.json.serializer.kmip.${pdot};

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.common.*;
import org.purpleBean.kmip.common.enumeration.*;
import org.purpleBean.kmip.codec.json.serializer.kmip.KmipDataTypeJsonSerializer;
import org.purpleBean.kmip.${pdot}.${STRUCTURE_NAME};

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

public class ${STRUCTURE_NAME}JsonSerializer extends KmipDataTypeJsonSerializer<${STRUCTURE_NAME}> {

    @Override
    public void serialize(${STRUCTURE_NAME} ${varname}, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        // Validation: Null check
        if (${varname} == null) {
            return;
        }

        // Validation: KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();
        if (!${varname}.isSupportedFor(spec)) {
            throw new UnsupportedEncodingException(String.format("%s is not supported for KMIP spec %s", ${varname}.getKmipTag().getDescription(), spec));
        }

        List<KmipDataType> fields = ${varname}.getValues();
        // Validation: Field compatibility with KMIP spec
        for (KmipDataType field : fields) {
            if (field != null && !field.isSupportedFor(spec)) {
                throw new UnsupportedEncodingException(String.format("%s in %s is not supported for KMIP spec %s",
                        field.getKmipTag().getDescription(), ${varname}.getKmipTag().getDescription(), spec));
            }
        }

        jsonGenerator.writeStartObject();
        jsonGenerator.writeObject(${varname}.getKmipTag());
        jsonGenerator.writeStringField("type", ${varname}.getEncodingType().getDescription());
        jsonGenerator.writeFieldName("value");
        jsonGenerator.writeStartArray();
        for (KmipDataType fieldValue : fields) {
            if (fieldValue != null) {
                jsonGenerator.writeObject(fieldValue);
            }
        }
        jsonGenerator.writeEndArray();
        jsonGenerator.writeEndObject();
    }
}
EOF

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

    if [ "${DRY_RUN}" = "true" ]; then
        echo "DRY RUN: would create JSON deserializer: ${out_file}"
        return 0
    fi

    mkdir -p "${out_dir}"

    cat > "${out_file}" <<EOF
package org.purpleBean.kmip.codec.json.deserializer.kmip.${pdot};

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.common.*;
import org.purpleBean.kmip.common.enumeration.*;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.${pdot}.${STRUCTURE_NAME};

import java.io.IOException;
import java.util.NoSuchElementException;

public class ${STRUCTURE_NAME}JsonDeserializer extends KmipDataTypeJsonDeserializer<${STRUCTURE_NAME}> {
    private final KmipTag kmipTag = ${STRUCTURE_NAME}.kmipTag;
    private final EncodingType encodingType = ${STRUCTURE_NAME}.encodingType;

    @Override
    public ${STRUCTURE_NAME} deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();
        if (node == null) {
            ctxt.reportInputMismatch(${STRUCTURE_NAME}.class, String.format("JSON node cannot be null for ${STRUCTURE_NAME} deserialization"));
            return null;
        }

        // Validation: Extract and validate KMIP tag
        KmipTag tag;
        try {
            tag = p.getCodec().treeToValue(node, KmipTag.class);
            if (tag == null) {
                ctxt.reportInputMismatch(${STRUCTURE_NAME}.class, String.format("Invalid KMIP tag for ${STRUCTURE_NAME}"));
                return null;
            }
        } catch (Exception e) {
            ctxt.reportInputMismatch(${STRUCTURE_NAME}.class, String.format("Failed to parse KMIP tag for ${STRUCTURE_NAME}: %s", e.getMessage()));
            return null;
        }

        if (!node.isObject() || tag.getValue().getValue() != kmipTag.getValue().getValue()) {
            ctxt.reportInputMismatch(${STRUCTURE_NAME}.class,
                    String.format("Expected object with %s tag for ${STRUCTURE_NAME}, got tag: %s", kmipTag.getValue().getValue(), tag.getValue().getValue()));
            return null;
        }

        // Validation: Extract and validate type field
        JsonNode typeNode = node.get("type");
        if (typeNode == null
                || !typeNode.isTextual()
                || EncodingType.fromName(typeNode.asText()).isEmpty()
                || EncodingType.fromName(typeNode.asText()).get() != encodingType
        ) {
            ctxt.reportInputMismatch(${STRUCTURE_NAME}.class, String.format("Missing or non-text 'type' field for ${STRUCTURE_NAME}"));
            return null;
        }

        // Validation: Extract and validate fields
        JsonNode values = node.get("value");
        if (values == null || !values.isArray() || values.isEmpty()) {
            ctxt.reportInputMismatch(${STRUCTURE_NAME}.class, "${STRUCTURE_NAME} 'value' must be a non-empty array");
            return null;
        }

        ${STRUCTURE_NAME}.${STRUCTURE_NAME}Builder builder = ${STRUCTURE_NAME}.builder();

        for (JsonNode valueNode : values) {
            if (!valueNode.has("tag")) {
                continue;
            }
            KmipTag.Value nodeTag = p.getCodec().treeToValue(valueNode, KmipTag.class).getValue();
            setValue(builder, nodeTag, valueNode, p, ctxt);
        }

        ${STRUCTURE_NAME} ${varname} = builder.build();

        // Validate KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();
        if (!${varname}.isSupportedFor(spec)) {
            throw new NoSuchElementException(String.format("${STRUCTURE_NAME} is not supported for KMIP spec %s", spec));
        }

        return ${varname};
    }

    /**
     * Sets the appropriate field in the builder based on the tag and value.
     *
     * @param builder the builder to set the field on
     * @param nodeTag the tag identifying the field to set
     * @param node    the JSON node containing the field value
     * @param p       the JsonParser
     * @param ctxt    the DeserializationContext
     * @throws IOException if there is an error deserializing the value
     */
    private void setValue(${STRUCTURE_NAME}.${STRUCTURE_NAME}Builder builder, KmipTag.Value nodeTag, JsonNode node, JsonParser p, DeserializationContext ctxt) throws IOException {
        // TODO: Implement field deserialization based on tag, preferably using switch case expression
        // Example:
        switch (nodeTag) {
            case KmipTag.Standard.ACTIVATION_DATE -> builder.activationDate(p.getCodec().treeToValue(node, ActivationDate.class));
            case KmipTag.Standard.STATE -> builder.state(p.getCodec().treeToValue(node, State.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }
}
EOF

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

    if [ "${DRY_RUN}" = "true" ]; then
        echo "DRY RUN: would create XML serializer: ${out_file}"
        return 0
    fi

    mkdir -p "${out_dir}"

    cat > "${out_file}" <<EOF
package org.purpleBean.kmip.codec.xml.serializer.kmip.${pdot};

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.common.*;
import org.purpleBean.kmip.common.enumeration.*;
import org.purpleBean.kmip.common.structure.*;
import org.purpleBean.kmip.codec.xml.serializer.kmip.KmipDataTypeXmlSerializer;
import org.purpleBean.kmip.${pdot}.${STRUCTURE_NAME};

import javax.xml.namespace.QName;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

public class ${STRUCTURE_NAME}XmlSerializer extends KmipDataTypeXmlSerializer<${STRUCTURE_NAME}> {

    @Override
    public void serialize(${STRUCTURE_NAME} ${varname}, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        // Validation: KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();
        if (!${varname}.isSupportedFor(spec)) {
            throw new UnsupportedEncodingException(String.format("%s not supported for KMIP spec %s", ${varname}.getClass().getSimpleName(), spec));
        }

        if (!(gen instanceof ToXmlGenerator xmlGen)) {
            throw new IllegalStateException("Expected ToXmlGenerator");
        }

        // Start element with name from kmipTag
        String elementName = ${varname}.getKmipTag().getDescription();
        xmlGen.setNextName(QName.valueOf(elementName));
        xmlGen.writeStartObject(${varname});

        // Serialize all fields
        List<KmipDataType> values = ${varname}.getValues();
        for (KmipDataType kmipDataType : values) {
            if (kmipDataType != null && kmipDataType.getKmipTag() != null) {
                serializers.defaultSerializeField(kmipDataType.getKmipTag().getDescription(), kmipDataType, gen);
            }
        }

        xmlGen.writeEndObject();
    }
}
EOF

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

    if [ "${DRY_RUN}" = "true" ]; then
        echo "DRY RUN: would create XML deserializer: ${out_file}"
        return 0
    fi

    mkdir -p "${out_dir}"

    cat > "${out_file}" <<EOF
package org.purpleBean.kmip.codec.xml.deserializer.kmip.${pdot};

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.dataformat.xml.deser.FromXmlParser;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.common.*;
import org.purpleBean.kmip.common.enumeration.*;
import org.purpleBean.kmip.common.structure.*;
import org.purpleBean.kmip.codec.xml.deserializer.kmip.KmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.${pdot}.${STRUCTURE_NAME};

import java.io.IOException;
import java.util.Map;

public class ${STRUCTURE_NAME}XmlDeserializer extends KmipDataTypeXmlDeserializer<${STRUCTURE_NAME}> {
    private final KmipTag kmipTag = ${STRUCTURE_NAME}.kmipTag;
    private final EncodingType encodingType = ${STRUCTURE_NAME}.encodingType;

    @Override
    public ${STRUCTURE_NAME} deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(${STRUCTURE_NAME}.class, "Expected XML object for ${STRUCTURE_NAME}");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
              && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(${STRUCTURE_NAME}.class, "Invalid Tag for ${STRUCTURE_NAME}");
            return null;
        }

        KmipSpec spec = KmipContext.getSpec();
        ${STRUCTURE_NAME}.${STRUCTURE_NAME}Builder builder = ${STRUCTURE_NAME}.builder();

        // Process all fields in the XML
        var fields = node.fields();
        while (fields.hasNext()) {
            Map.Entry<String, JsonNode> entry = fields.next();
            KmipTag.Value nodeTag = KmipTag.fromName(spec, entry.getKey());
            setValue(builder, nodeTag, entry.getValue(), p, ctxt);
        }

        ${STRUCTURE_NAME} ${varname} = builder.build();

        if (!${varname}.isSupportedFor(spec)) {
            ctxt.reportInputMismatch(${STRUCTURE_NAME}.class, "${STRUCTURE_NAME} not supported for spec " + spec);
            return null;
        }

        return ${varname};
    }

    /**
     * Sets the appropriate field in the builder based on the tag and value.
     *
     * @param builder the builder to set the field on
     * @param nodeTag the tag identifying the field to set
     * @param node    the XML node containing the field value
     * @param p       the JsonParser
     * @param ctxt    the DeserializationContext
     * @throws IOException if there is an error deserializing the value
     */
    private void setValue(${STRUCTURE_NAME}.${STRUCTURE_NAME}Builder builder, KmipTag.Value nodeTag, JsonNode node, JsonParser p, DeserializationContext ctxt) throws IOException {
        // TODO: Implement field deserialization based on nodeTag
        // Example:
        switch (nodeTag) {
            case KmipTag.Standard.ACTIVATION_DATE -> builder.activationDate(p.getCodec().treeToValue(node, ActivationDate.class));
            case KmipTag.Standard.STATE -> builder.state(p.getCodec().treeToValue(node, State.class));
            default -> throw new IllegalArgumentException();
        }
    }
}
EOF

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

    if [ "${DRY_RUN}" = "true" ]; then
        echo "DRY RUN: would create TTLV serializer: ${out_file}"
        return 0
    fi

    mkdir -p "${out_dir}"

    cat > "${out_file}" <<EOF
package org.purpleBean.kmip.codec.ttlv.serializer.kmip.${pdot};

import org.purpleBean.kmip.*;
import org.purpleBean.kmip.codec.ttlv.TtlvObject;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.codec.ttlv.serializer.kmip.KmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.${pdot}.${STRUCTURE_NAME};

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public class ${STRUCTURE_NAME}TtlvSerializer extends KmipDataTypeTtlvSerializer<${STRUCTURE_NAME}> {
    @Override
    public ByteBuffer serialize(${STRUCTURE_NAME} value, TtlvMapper mapper) throws IOException {
        return serializeToTtlvObject(value, mapper).toByteBuffer();
    }

    private TtlvObject serializeToTtlvObject(${STRUCTURE_NAME} value, TtlvMapper mapper) throws IOException {
        KmipSpec spec = KmipContext.getSpec();
        if (!value.isSupportedFor(spec)) {
            throw new UnsupportedEncodingException(String.format("%s not supported for KMIP spec %s", value.getClass().getSimpleName(), spec));
        }

        List<KmipDataType> nestedValues = value.getValues();
        byte[] tag = value.getKmipTag().getTagBytes();
        byte type = value.getEncodingType().getTypeValue();

        List<ByteBuffer> nestedObjects = new ArrayList<ByteBuffer>();
        for (KmipDataType object : nestedValues) {
            if (object != null) {
                nestedObjects.add(mapper.writeValueAsByteBuffer(object));
            }
        }

        int totalLength = nestedObjects.stream().mapToInt(ByteBuffer::remaining).sum();
        ByteBuffer payloadBuffer = ByteBuffer.allocate(totalLength);
        nestedObjects.forEach(payloadBuffer::put);
        byte[] payload = payloadBuffer.array();

        return TtlvObject.builder()
                .tag(tag)
                .type(type)
                .value(payload)
                .build();
    }
}
EOF

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

    if [ "${DRY_RUN}" = "true" ]; then
        echo "DRY RUN: would create TTLV deserializer: ${out_file}"
        return 0
    fi

    mkdir -p "${out_dir}"

    cat > "${out_file}" <<EOF
package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.${pdot};

import org.purpleBean.kmip.*;
import org.purpleBean.kmip.common.*;
import org.purpleBean.kmip.common.enumeration.*;
import org.purpleBean.kmip.common.structure.*;
import org.purpleBean.kmip.codec.ttlv.TtlvObject;
import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.KmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.${pdot}.${STRUCTURE_NAME};

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

public class ${STRUCTURE_NAME}TtlvDeserializer extends KmipDataTypeTtlvDeserializer<${STRUCTURE_NAME}> {
    private final KmipTag kmipTag = ${STRUCTURE_NAME}.kmipTag;
    private final EncodingType encodingType = ${STRUCTURE_NAME}.encodingType;

    @Override
    public ${STRUCTURE_NAME} deserialize(ByteBuffer ttlvBuffer, TtlvMapper mapper) throws IOException {
        TtlvObject obj = TtlvObject.fromBuffer(ttlvBuffer);
        if (Arrays.equals(obj.getTag(), kmipTag.getTagBytes()) && obj.getType() != encodingType.getTypeValue()) {
            throw new IllegalArgumentException(String.format("Expected %s type for %s, got %s", encodingType.getTypeValue(), kmipTag.getDescription(), obj.getType()));
        }

        List<TtlvObject> nestedObjects = TtlvObject.fromBytesMultiple(obj.getValue());
        KmipSpec spec = KmipContext.getSpec();
        ${STRUCTURE_NAME}.${STRUCTURE_NAME}Builder builder = ${STRUCTURE_NAME}.builder();

        for (TtlvObject ttlvObject : nestedObjects) {
            KmipTag.Value nodeTag = KmipTag.fromBytes(spec, ttlvObject.getTag());
            setValue(builder, nodeTag, ttlvObject, mapper);
        }

        ${STRUCTURE_NAME} ${varname} = builder.build();

        if (!${varname}.isSupportedFor(spec)) {
            throw new NoSuchElementException(String.format("%s is not supported for KMIP spec %s", ${varname}.getClass().getSimpleName(), spec));
        }
        return ${varname};
    }

    private void setValue(${STRUCTURE_NAME}.${STRUCTURE_NAME}Builder builder, KmipTag.Value nodeTag, TtlvObject ttlvObject, TtlvMapper mapper) throws IOException {
        // TODO: Implement field deserialization based on nodeTag
        // Example:
        switch (nodeTag) {
            case KmipTag.Standard.ACTIVATION_DATE -> builder.activationDate(mapper.readValue(ttlvObject.toByteBuffer(), ActivationDate.class));
            case KmipTag.Standard.STATE -> builder.state(mapper.readValue(ttlvObject.toByteBuffer(), State.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }
}
EOF

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

    if [ "${DRY_RUN}" = "true" ]; then
        echo "DRY RUN: would create domain test: ${out_file}"
        return 0
    fi

    mkdir -p "${out_dir}"

    cat > "${out_file}" <<EOF
package org.purpleBean.kmip.${pdot};

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipDataType;
import org.purpleBean.kmip.common.ActivationDate;
import org.purpleBean.kmip.common.enumeration.State;
import org.purpleBean.kmip.test.suite.AbstractKmipStructureSuite;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;

@DisplayName("${STRUCTURE_NAME} Domain Tests")
class ${STRUCTURE_NAME}Test extends AbstractKmipStructureSuite<${STRUCTURE_NAME}> {

    private static final OffsetDateTime FIXED_TIME = OffsetDateTime.of(2024, 1, 2, 3, 4, 5, 0, ZoneOffset.UTC);

    @Override
    protected Class<${STRUCTURE_NAME}> type() {
        return ${STRUCTURE_NAME}.class;
    }

    @Override
    protected ${STRUCTURE_NAME} createDefault() {
        // TODO: Update with actual default values for your structure
        ActivationDate activationDate = ActivationDate.builder().value(FIXED_TIME).build();
        State state = new State(State.Standard.ACTIVE);
        return ${STRUCTURE_NAME}.builder()
            .activationDate(activationDate)
            .state(state)
            .build();
    }

    @Override
    protected EncodingType expectedEncodingType() {
        return EncodingType.STRUCTURE;
    }

    @Override
    protected int expectedMinComponentCount() {
        // TODO: Update with the expected minimum number of components
        return 2;
    }

    @Override
    protected void validateComponents(List<KmipDataType> values) {
        // Add assertions for components if desired
        // TODO: Add validation for each component
        // Example:
        // assertThat(values.get(0).getEncodingType()).isEqualTo(EncodingType.DATE_TIME);
        // assertThat(values.get(1).getEncodingType()).isEqualTo(EncodingType.ENUMERATION);
    }
}
EOF

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

    if [ "${DRY_RUN}" = "true" ]; then
        echo "DRY RUN: would create ${format} serialization test: ${out_file}"
        return 0
    fi

    mkdir -p "${out_dir}"

    cat > "${out_file}" <<EOF
package org.purpleBean.kmip.codec.${format}.${pdot};

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.${pdot}.${STRUCTURE_NAME};
import org.purpleBean.kmip.test.suite.Abstract${format_pascal}SerializationSuite;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.common.*;
import org.purpleBean.kmip.common.enumeration.*;
import org.purpleBean.kmip.common.structure.*;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@DisplayName("${STRUCTURE_NAME} ${format_upper} Serialization Tests")
class ${suite_name} extends Abstract${format_pascal}SerializationSuite<${STRUCTURE_NAME}> {

    private static final OffsetDateTime FIXED_TIME = OffsetDateTime.of(2024, 1, 2, 3, 4, 5, 0, ZoneOffset.UTC);

    @Override
    protected Class<${STRUCTURE_NAME}> type() {
        return ${STRUCTURE_NAME}.class;
    }

    @Override
    protected ${STRUCTURE_NAME} createDefault() {
        // TODO: Update with actual default values for your structure
        ActivationDate activationDate = ActivationDate.builder().value(FIXED_TIME).build();
        State state = new State(State.Standard.ACTIVE);
        return ${STRUCTURE_NAME}.builder()
            .activationDate(activationDate)
            .state(state)
            .build();
    }

    @Override
    protected ${STRUCTURE_NAME} createVariant() {
        // TODO: Update with different values to test variations
        ActivationDate activationDate = ActivationDate.builder().value(FIXED_TIME.plusDays(1)).build();
        State state = new State(State.Standard.DEACTIVATED);
        return ${STRUCTURE_NAME}.builder()
            .activationDate(activationDate)
            .state(state)
            .build();
    }
}
EOF

    echo "Created: ${out_file}"
}

generate_benchmark_subject() {
    local STRUCTURE_NAME="$1"
    local out_dir="${TEST_JAVA}/benchmark/subjects/${SUB_PATH}"
    local out_file="${out_dir}/${STRUCTURE_NAME}BenchmarkSubject.java"
    local pdot
    pdot="$(pkg_dot)"

    if [ "${DRY_RUN}" = "true" ]; then
        echo "DRY RUN: would create benchmark subject: ${out_file}"
        return 0
    fi

    mkdir -p "${out_dir}"

    cat > "${out_file}" <<EOF
package org.purpleBean.kmip.benchmark.subjects.${pdot};

import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.Getter;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.common.*;
import org.purpleBean.kmip.common.enumeration.*;
import org.purpleBean.kmip.common.structure.*;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.codec.KmipCodecManager;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.${pdot}.${STRUCTURE_NAME};

import java.nio.ByteBuffer;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

public class ${STRUCTURE_NAME}BenchmarkSubject extends KmipBenchmarkSubject {

    private JsonMapper json;
    private XmlMapper xml;
    private TtlvMapper ttlv;

    private ${STRUCTURE_NAME} obj;

    @Getter
    private String jsonStr;
    @Getter
    private String xmlStr;
    @Getter
    private ByteBuffer ttlvBuf;

    public ${STRUCTURE_NAME}BenchmarkSubject() throws Exception {
        this.setup();
    }

    @Override
    public String name() {
        return "${STRUCTURE_NAME}";
    }

    @Override
    public void setup() throws Exception {
        json = KmipCodecManager.getJsonMapper();
        xml = KmipCodecManager.getXmlMapper();
        ttlv = KmipCodecManager.getTtlvMapper();

        var fixed = OffsetDateTime.of(2024, 1, 2, 3, 4, 5, 0, ZoneOffset.UTC);
        ActivationDate activationDate = ActivationDate.builder().value(fixed).build();
        State state = new State(State.Standard.ACTIVE);
        obj = ${STRUCTURE_NAME}.builder()
            .activationDate(activationDate)
            .state(state)
            .build();

        jsonStr = json.writeValueAsString(obj);
        xmlStr = xml.writeValueAsString(obj);
        ttlvBuf = ttlv.writeValueAsByteBuffer(obj);
    }

    @Override
    public void tearDown() {
        KmipContext.clear();
    }

    @Override
    public String jsonSerialize() throws Exception {
        return json.writeValueAsString(obj);
    }

    @Override
    public Object jsonDeserialize() throws Exception {
        return json.readValue(jsonStr, ${STRUCTURE_NAME}.class);
    }

    @Override
    public String xmlSerialize() throws Exception {
        return xml.writeValueAsString(obj);
    }

    @Override
    public Object xmlDeserialize() throws Exception {
        return xml.readValue(xmlStr, ${STRUCTURE_NAME}.class);
    }

    @Override
    public ByteBuffer ttlvSerialize() throws Exception {
        return ttlv.writeValueAsByteBuffer(obj);
    }

    @Override
    public Object ttlvDeserialize() throws Exception {
        return ttlv.readValue(ttlvBuf.duplicate(), ${STRUCTURE_NAME}.class);
    }
}
EOF

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

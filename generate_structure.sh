#!/bin/bash
set -euo pipefail

#############################################
# Globals & Constants
#############################################
BASE_DIR="$(pwd)"
MAIN_JAVA="src/main/java/org/purpleBean/kmip"
TEST_JAVA="src/test/java/org/purpleBean/kmip"
SUB_PATH="common"

# Flags (default: off)
GEN_CLASS=false
GEN_JSON_SER=false
GEN_JSON_DES=false
GEN_XML_SER=false
GEN_XML_DES=false
GEN_TTLV_SER=false
GEN_TTLV_DES=false
GEN_TEST_JSON=false
GEN_TEST_XML=false
GEN_TEST_TTLV=false
GEN_BENCHMARK=false

# Dry run flag (set to true when no flags provided)
DRY_RUN=false

#############################################
# Helpers
#############################################
usage() {
    cat <<EOF
Usage: $0 [options] <Attribute1> [Attribute2...]

If no options are provided the script performs a dry run (prints what it would do).
To actually create files pass one or more generation flags.

Options:
  --class        Generate Attribute class
  --json-ser     Generate JSON Serializer
  --json-des     Generate JSON Deserializer
  --xml-ser      Generate XML Serializer
  --xml-des      Generate XML Deserializer
  --ttlv-ser     Generate TTLV Serializer
  --ttlv-des     Generate TTLV Deserializer
  --test-json    Generate JSON Test
  --test-xml     Generate XML Test
  --test-ttlv    Generate TTLV Test
  --benchmark    Generate Benchmark Subject
  --all          Generate all files

Examples:
  # Dry run (no files will be written)
  ./generate_attribute.sh ActivationDate

  # Generate only class and json serializer
  ./generate_attribute.sh --class --json-ser ActivationDate

  # Generate everything
  ./generate_attribute.sh --all ActivationDate DeactivationDate
EOF
    exit 1
}

get_attribute_var_name() {
    local name="$1"
    echo "${name:0:1}" | tr '[:upper:]' '[:lower:]'"${name:1}"
}

to_snake_upper() {
    local name="$1"
    echo "$name" | sed -r 's/([A-Z])/_\1/g' | sed 's/^_//' | tr '[:lower:]' '[:upper:]'
}

#############################################
# Directory Management
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
        echo "  ${test_java}/codec/json/${sub_path}"
        echo "  ${test_java}/codec/xml/${sub_path}"
        echo "  ${test_java}/codec/ttlv/${sub_path}"
        echo "  ${test_java}/benchmark/subjects/${sub_path}"
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
}

#############################################
# Service Registration
#############################################

add_service_entry() {
    local file="$1"
    local entry="$2"

    if [[ "${DRY_RUN}" == "true" ]]; then
        echo "DRY RUN: would add service entry:"
        echo "  file: ${file}"
        echo "  entry: ${entry}"
        return 0
    fi

    mkdir -p "$(dirname "$file")"
    touch "$file"

    if ! grep -qF "$entry" "$file"; then
        echo "$entry" >> "$file"
    fi

    local temp_file="${file}.tmp"
    sort -u "$file" | grep -v '^[[:space:]]*$' > "$temp_file"

    if ! cmp -s "$file" "$temp_file"; then
        mv "$temp_file" "$file"
    else
        rm -f "$temp_file"
    fi
}

register_services() {
    local ATTRIBUTE_NAME="$1"
    local sub_path="$2"

    # If dry run, the add_service_entry will only print; otherwise it will actually update files
    add_service_entry "src/main/resources/META-INF/services/org.purpleBean.kmip.codec.json.serializer.kmip.KmipDataTypeJsonSerializer" \
        "org.purpleBean.kmip.codec.json.serializer.kmip.${sub_path}.${ATTRIBUTE_NAME}AttributeJsonSerializer"

    add_service_entry "src/main/resources/META-INF/services/org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer" \
        "org.purpleBean.kmip.codec.json.deserializer.kmip.${sub_path}.${ATTRIBUTE_NAME}AttributeJsonDeserializer"

    add_service_entry "src/main/resources/META-INF/services/org.purpleBean.kmip.codec.xml.serializer.kmip.KmipDataTypeXmlSerializer" \
        "org.purpleBean.kmip.codec.xml.serializer.kmip.${sub_path}.${ATTRIBUTE_NAME}AttributeXmlSerializer"

    add_service_entry "src/main/resources/META-INF/services/org.purpleBean.kmip.codec.xml.deserializer.kmip.KmipDataTypeXmlDeserializer" \
        "org.purpleBean.kmip.codec.xml.deserializer.kmip.${sub_path}.${ATTRIBUTE_NAME}AttributeXmlDeserializer"

    add_service_entry "src/main/resources/META-INF/services/org.purpleBean.kmip.codec.ttlv.serializer.kmip.KmipDataTypeTtlvSerializer" \
        "org.purpleBean.kmip.codec.ttlv.serializer.kmip.${sub_path}.${ATTRIBUTE_NAME}AttributeTtlvSerializer"

    add_service_entry "src/main/resources/META-INF/services/org.purpleBean.kmip.codec.ttlv.deserializer.kmip.KmipDataTypeTtlvDeserializer" \
        "org.purpleBean.kmip.codec.ttlv.deserializer.kmip.${sub_path}.${ATTRIBUTE_NAME}AttributeTtlvDeserializer"

    add_service_entry "src/test/resources/META-INF/services/org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject" \
        "org.purpleBean.kmip.benchmark.subjects.${sub_path}.${ATTRIBUTE_NAME}AttributeBenchmarkSubject"
}

#############################################
# File Generators (one per artifact)
# Each generator respects DRY_RUN: when DRY_RUN=true it only prints the path it would create.
#############################################

generate_attribute_class() {
    local ATTRIBUTE_NAME="$1"
    local ATTRIBUTE_NAME_SNAKE="$2"
    local ATTRIBUTE_VAR_NAME="$3"
    local path="${MAIN_JAVA}/${SUB_PATH}/${ATTRIBUTE_NAME}Attribute.java"

    if [[ "${DRY_RUN}" == "true" ]]; then
        echo "DRY RUN: would create file: ${path}"
        return 0
    fi

    mkdir -p "${MAIN_JAVA}/${SUB_PATH}"
    cat > "${path}" << EOF
package org.purpleBean.kmip.${SUB_PATH};

import lombok.*;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.common.enumeration.State;

import java.time.OffsetDateTime;
import java.util.Objects;
import java.util.Set;

/**
 * KMIP ${ATTRIBUTE_NAME} attribute.
 */
@Data
@Builder
public class ${ATTRIBUTE_NAME}Attribute implements KmipAttribute {
    private final KmipTag kmipTag = new KmipTag(KmipTag.Standard.${ATTRIBUTE_NAME_SNAKE});
    private final EncodingType encodingType = EncodingType.DATE_TIME; // TODO : update the encoding type

    // Template supported versions — adjust as needed
    private final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion );

    // TODO : update the capability flags
    // Capability flags — adjust based on attribute semantics
    private final boolean alwaysPresent = false;
    private final boolean serverInitializable = true;
    private final boolean clientInitializable = true;
    private final boolean clientDeletable = false;
    private final boolean multiInstanceAllowed = false;

    @NonNull
    private final OffsetDateTime dateTime;  // TODO : update the field type and name

    @Override
    public boolean isClientModifiable(@NonNull State state) {
        // PRE_ACTIVE is modifiable by default, adjust as needed
        return state.getValue().getValue() == State.Standard.PRE_ACTIVE.getValue(); // TODO : set conditions for client modifiable
    }

    @Override
    public boolean isServerModifiable(@NonNull State state) {
        // PRE_ACTIVE is modifiable by default, adjust as needed
        return state.getValue().getValue() == State.Standard.PRE_ACTIVE.getValue(); // TODO : set conditions for server modifiable
    }

    @Override
    public boolean isSupportedFor(@NonNull KmipSpec spec) {
        return supportedVersions.contains(spec);
    }

    // TODO : override equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ${ATTRIBUTE_NAME}Attribute that = (${ATTRIBUTE_NAME}Attribute) o;
        // Compare OffsetDateTime up to seconds to avoid flakiness
        return this.dateTime.withNano(0).equals(that.dateTime.withNano(0));
    }

    // TODO : override hashCode
    @Override
    public int hashCode() {
        return Objects.hash(dateTime.withNano(0));
    }
}
EOF
}

generate_json_serializer() {
    local ATTRIBUTE_NAME="$1"
    local ATTRIBUTE_NAME_SNAKE="$2"
    local path="${MAIN_JAVA}/codec/json/serializer/kmip/${SUB_PATH}/${ATTRIBUTE_NAME}AttributeJsonSerializer.java"

    if [[ "${DRY_RUN}" == "true" ]]; then
        echo "DRY RUN: would create file: ${path}"
        return 0
    fi

    mkdir -p "${MAIN_JAVA}/codec/json/serializer/kmip/${SUB_PATH}"
    cat > "${path}" << EOF
package org.purpleBean.kmip.codec.json.serializer.kmip.${SUB_PATH};

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.codec.json.serializer.kmip.KmipDataTypeJsonSerializer;
import org.purpleBean.kmip.${SUB_PATH}.${ATTRIBUTE_NAME}Attribute;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * JSON serializer for ${ATTRIBUTE_NAME}.
 */
public class ${ATTRIBUTE_NAME}AttributeJsonSerializer extends KmipDataTypeJsonSerializer<${ATTRIBUTE_NAME}Attribute> {

    @Override
    public void serialize(${ATTRIBUTE_NAME}Attribute value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        if (value == null) return;

        KmipSpec spec = KmipContext.getSpec();
        if (!value.isSupportedFor(spec)) {
            throw new UnsupportedEncodingException(
                String.format("%s is not supported for KMIP spec %s", value.getKmipTag().getDescription(), spec)
            );
        }

        gen.writeStartObject();
        gen.writeObject(value.getKmipTag());
        gen.writeStringField("type", value.getEncodingType().getDescription());
        gen.writeStringField("value", value.getDateTime().toString());
        gen.writeEndObject();
    }
}
EOF
}

generate_json_deserializer() {
    local ATTRIBUTE_NAME="$1"
    local ATTRIBUTE_NAME_SNAKE="$2"
    local path="${MAIN_JAVA}/codec/json/deserializer/kmip/${SUB_PATH}/${ATTRIBUTE_NAME}AttributeJsonDeserializer.java"

    if [[ "${DRY_RUN}" == "true" ]]; then
        echo "DRY RUN: would create file: ${path}"
        return 0
    fi

    mkdir -p "${MAIN_JAVA}/codec/json/deserializer/kmip/${SUB_PATH}"
    cat > "${path}" << EOF
package org.purpleBean.kmip.codec.json.deserializer.kmip.${SUB_PATH};

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.${SUB_PATH}.${ATTRIBUTE_NAME}Attribute;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.util.NoSuchElementException;

/**
 * JSON deserializer for ${ATTRIBUTE_NAME}.
 */
public class ${ATTRIBUTE_NAME}AttributeJsonDeserializer extends KmipDataTypeJsonDeserializer<${ATTRIBUTE_NAME}Attribute> {
    private final KmipTag kmipTag = new KmipTag(KmipTag.Standard.${ATTRIBUTE_NAME_SNAKE});
    private final EncodingType encodingType = EncodingType.DATE_TIME; // TODO : update the encoding type

    @Override
    public ${ATTRIBUTE_NAME}Attribute deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();

        if (node == null) {
            ctxt.reportInputMismatch(${ATTRIBUTE_NAME}Attribute.class, String.format("JSON node cannot be null for ${ATTRIBUTE_NAME}Attribute deserialization"));
            return null;
        }

        // Validation: Extract and validate KMIP tag
        KmipTag tag;
        try {
            tag = p.getCodec().treeToValue(node, KmipTag.class);
            if (tag == null) {
                ctxt.reportInputMismatch(${ATTRIBUTE_NAME}Attribute.class, String.format("Invalid KMIP tag for ${ATTRIBUTE_NAME}Attribute"));
                return null;
            }
        } catch (Exception e) {
            ctxt.reportInputMismatch(${ATTRIBUTE_NAME}Attribute.class, String.format("Failed to parse KMIP tag for ${ATTRIBUTE_NAME}Attribute: %s", e.getMessage()));
            return null;
        }

        if (!node.isObject() || tag.getValue().getValue() != kmipTag.getValue().getValue()) {
            ctxt.reportInputMismatch(${ATTRIBUTE_NAME}Attribute.class, "Expected object for ${ATTRIBUTE_NAME}Attribute");
            return null;
        }

        // Validation: Extract and validate type field
        JsonNode typeNode = node.get("type");
        if (typeNode == null
                || !typeNode.isTextual()
                || EncodingType.fromName(typeNode.asText()).isEmpty()
                || EncodingType.fromName(typeNode.asText()).get() != encodingType
        ) {
            ctxt.reportInputMismatch(${ATTRIBUTE_NAME}Attribute.class, String.format("Missing or non-text 'type' field for ${ATTRIBUTE_NAME}Attribute"));
            return null;
        }

        // Validation: Extract and validate value field
        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(${ATTRIBUTE_NAME}Attribute.class, "Missing or non-text 'value' for ${ATTRIBUTE_NAME}Attribute");
            return null;
        }

        // TODO : update the field type and name
        OffsetDateTime dateTime = OffsetDateTime.parse(valueNode.asText());
        ${ATTRIBUTE_NAME}Attribute attribute = ${ATTRIBUTE_NAME}Attribute.builder().dateTime(dateTime).build();

        KmipSpec spec = KmipContext.getSpec();
        if (!attribute.isSupportedFor(spec)) {
            throw new NoSuchElementException(
                    String.format("${ATTRIBUTE_NAME}Attribute '%s' is not supported for KMIP spec %s", valueNode.asText(), spec)
            );
        }
        return attribute;
    }
}
EOF
}

generate_xml_serializer() {
    local ATTRIBUTE_NAME="$1"
    local ATTRIBUTE_NAME_SNAKE="$2"
    local path="${MAIN_JAVA}/codec/xml/serializer/kmip/${SUB_PATH}/${ATTRIBUTE_NAME}AttributeXmlSerializer.java"

    if [[ "${DRY_RUN}" == "true" ]]; then
        echo "DRY RUN: would create file: ${path}"
        return 0
    fi

    mkdir -p "${MAIN_JAVA}/codec/xml/serializer/kmip/${SUB_PATH}"
    cat > "${path}" << EOF
package org.purpleBean.kmip.codec.xml.serializer.kmip.${SUB_PATH};

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.codec.xml.serializer.kmip.KmipDataTypeXmlSerializer;
import org.purpleBean.kmip.${SUB_PATH}.${ATTRIBUTE_NAME}Attribute;

import javax.xml.namespace.QName;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * XML serializer for ${ATTRIBUTE_NAME}.
 */
public class ${ATTRIBUTE_NAME}AttributeXmlSerializer extends KmipDataTypeXmlSerializer<${ATTRIBUTE_NAME}Attribute> {

    @Override
    public void serialize(${ATTRIBUTE_NAME}Attribute value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        KmipSpec spec = KmipContext.getSpec();
        if (!value.isSupportedFor(spec)) {
            throw new UnsupportedEncodingException();
        }

        if (!(gen instanceof ToXmlGenerator xmlGen)) {
            throw new IllegalStateException("Expected ToXmlGenerator");
        }

        // Start element with name from kmipTag
        String elementName = value.getKmipTag().getDescription();
        xmlGen.setNextName(QName.valueOf(elementName));
        xmlGen.writeStartObject(value);

        xmlGen.setNextIsAttribute(true);
        xmlGen.writeStringField("type", value.getEncodingType().getDescription());
        xmlGen.setNextIsAttribute(true);
        xmlGen.writeStringField("value", value.getDateTime().toString());
        xmlGen.writeEndObject();
    }
}
EOF
}

generate_xml_deserializer() {
    local ATTRIBUTE_NAME="$1"
    local ATTRIBUTE_NAME_SNAKE="$2"
    local path="${MAIN_JAVA}/codec/xml/deserializer/kmip/${SUB_PATH}/${ATTRIBUTE_NAME}AttributeXmlDeserializer.java"

    if [[ "${DRY_RUN}" == "true" ]]; then
        echo "DRY RUN: would create file: ${path}"
        return 0
    fi

    mkdir -p "${MAIN_JAVA}/codec/xml/deserializer/kmip/${SUB_PATH}"
    cat > "${path}" << EOF
package org.purpleBean.kmip.codec.xml.deserializer.kmip.${SUB_PATH};

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.dataformat.xml.deser.FromXmlParser;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.xml.deserializer.kmip.KmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.${SUB_PATH}.${ATTRIBUTE_NAME}Attribute;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.util.NoSuchElementException;

/**
 * XML deserializer for ${ATTRIBUTE_NAME}.
 */
public class ${ATTRIBUTE_NAME}AttributeXmlDeserializer extends KmipDataTypeXmlDeserializer<${ATTRIBUTE_NAME}Attribute> {
    private final KmipTag kmipTag = new KmipTag(KmipTag.Standard.${ATTRIBUTE_NAME_SNAKE});
    private final EncodingType encodingType = EncodingType.DATE_TIME; // TODO : update the encoding type

    @Override
    public ${ATTRIBUTE_NAME}Attribute deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(${ATTRIBUTE_NAME}Attribute.class, "Expected XML element object for ${ATTRIBUTE_NAME}Attribute");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
                && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(${ATTRIBUTE_NAME}Attribute.class, "Invalid Tag for ${ATTRIBUTE_NAME}Attribute");
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() ||
                !encodingType.getDescription().equals(typeNode.asText())) {
            ctxt.reportInputMismatch(${ATTRIBUTE_NAME}Attribute.class, "Missing or invalid '@type' attribute for ${ATTRIBUTE_NAME}Attribute");
            return null;
        }

        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(${ATTRIBUTE_NAME}Attribute.class,
                "Missing or non-text 'value' for ${ATTRIBUTE_NAME}Attribute");
            return null;
        }

        OffsetDateTime dateTime = OffsetDateTime.parse(valueNode.asText());
        ${ATTRIBUTE_NAME}Attribute attribute = ${ATTRIBUTE_NAME}Attribute.builder()
            .dateTime(dateTime)
            .build();

        KmipSpec spec = KmipContext.getSpec();
        if (!attribute.isSupportedFor(spec)) {
            throw new NoSuchElementException(
                String.format("${ATTRIBUTE_NAME}Attribute '%s' not supported for spec %s", kmipTag.getDescription(), spec));

        }
        return attribute;
    }
}
EOF
}

generate_ttlv_serializer() {
    local ATTRIBUTE_NAME="$1"
    local ATTRIBUTE_NAME_SNAKE="$2"
    local path="${MAIN_JAVA}/codec/ttlv/serializer/kmip/${SUB_PATH}/${ATTRIBUTE_NAME}AttributeTtlvSerializer.java"

    if [[ "${DRY_RUN}" == "true" ]]; then
        echo "DRY RUN: would create file: ${path}"
        return 0
    fi

    mkdir -p "${MAIN_JAVA}/codec/ttlv/serializer/kmip/${SUB_PATH}"
    cat > "${path}" << EOF
package org.purpleBean.kmip.codec.ttlv.serializer.kmip.${SUB_PATH};

import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.codec.ttlv.TtlvObject;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.codec.ttlv.serializer.kmip.KmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.${SUB_PATH}.${ATTRIBUTE_NAME}Attribute;

import java.io.IOException;
import java.nio.ByteBuffer;

public class ${ATTRIBUTE_NAME}AttributeTtlvSerializer extends KmipDataTypeTtlvSerializer<${ATTRIBUTE_NAME}Attribute> {

    @Override
    public ByteBuffer serialize(${ATTRIBUTE_NAME}Attribute value, TtlvMapper mapper) throws IOException {
        return serializeToTtlvObject(value, mapper).toByteBuffer();
    }

    public TtlvObject serializeToTtlvObject(${ATTRIBUTE_NAME}Attribute value, TtlvMapper mapper) throws IOException {
        if (value == null) {
            return null;
        }

        KmipSpec spec = KmipContext.getSpec();
        if (!value.isSupportedFor(spec)) {
            throw new IOException(
                String.format("%s is not supported for KMIP spec %s",
                value.getKmipTag().getDescription(), spec)
            );
        }

        byte[] tag = value.getKmipTag().getTagBytes();
        byte type = value.getEncodingType().getTypeValue();
        byte[] payload = mapper.writeValueAsByteBuffer(value.getDateTime()).array();

        return TtlvObject.builder()
                .tag(tag)
                .type(type)
                .value(payload)
                .build();
    }
}
EOF
}

generate_ttlv_deserializer() {
    local ATTRIBUTE_NAME="$1"
    local ATTRIBUTE_NAME_SNAKE="$2"
    local path="${MAIN_JAVA}/codec/ttlv/deserializer/kmip/${SUB_PATH}/${ATTRIBUTE_NAME}AttributeTtlvDeserializer.java"

    if [[ "${DRY_RUN}" == "true" ]]; then
        echo "DRY RUN: would create file: ${path}"
        return 0
    fi

    mkdir -p "${MAIN_JAVA}/codec/ttlv/deserializer/kmip/${SUB_PATH}"
    cat > "${path}" << EOF
package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.${SUB_PATH};

import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.ttlv.TtlvConstants;
import org.purpleBean.kmip.codec.ttlv.TtlvObject;
import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.KmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.${SUB_PATH}.${ATTRIBUTE_NAME}Attribute;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.NoSuchElementException;

public class ${ATTRIBUTE_NAME}AttributeTtlvDeserializer extends KmipDataTypeTtlvDeserializer<${ATTRIBUTE_NAME}Attribute> {
    private final KmipTag kmipTag = new KmipTag(KmipTag.Standard.${ATTRIBUTE_NAME_SNAKE});
    private final EncodingType encodingType = EncodingType.DATE_TIME; // TODO : update the encoding type

    @Override
    public ${ATTRIBUTE_NAME}Attribute deserialize(ByteBuffer ttlvBuffer, TtlvMapper mapper) throws IOException {
        TtlvObject obj = TtlvObject.fromBuffer(ttlvBuffer);
        if (Arrays.equals(obj.getTag(), kmipTag.getTagBytes())
                && obj.getType() != encodingType.getTypeValue()) {
            throw new IllegalArgumentException(String.format("Expected %s type for %s", encodingType.getTypeValue(), kmipTag.getDescription()));
        }
        ByteBuffer bb = ByteBuffer.wrap(obj.getValue()).order(TtlvConstants.BYTE_ORDER);
        OffsetDateTime dt = mapper.readValue(bb, OffsetDateTime.class);

        KmipSpec spec = KmipContext.getSpec();
        ${ATTRIBUTE_NAME}Attribute attribute = ${ATTRIBUTE_NAME}Attribute.builder().dateTime(dt).build();

        if (!attribute.isSupportedFor(spec)) {
            throw new NoSuchElementException();
        }
        return attribute;
    }
}
EOF
}

generate_json_test() {
    local ATTRIBUTE_NAME="$1"
    local path="${TEST_JAVA}/codec/json/${SUB_PATH}/${ATTRIBUTE_NAME}AttributeJsonTest.java"

    if [[ "${DRY_RUN}" == "true" ]]; then
        echo "DRY RUN: would create file: ${path}"
        return 0
    fi

    mkdir -p "${TEST_JAVA}/codec/json/${SUB_PATH}"
    cat > "${path}" << EOF
package org.purpleBean.kmip.codec.json.${SUB_PATH};

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.${SUB_PATH}.${ATTRIBUTE_NAME}Attribute;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationSuite;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@DisplayName("${ATTRIBUTE_NAME}Attribute JSON Serialization Tests")
class ${ATTRIBUTE_NAME}AttributeJsonTest extends AbstractJsonSerializationSuite<${ATTRIBUTE_NAME}Attribute> {

    private static final OffsetDateTime FIXED_TIME = OffsetDateTime.of(2024, 1, 2, 3, 4, 5, 0, ZoneOffset.UTC);

    @Override
    protected Class<${ATTRIBUTE_NAME}Attribute> type() {
        return ${ATTRIBUTE_NAME}Attribute.class;
    }

    @Override
    protected ${ATTRIBUTE_NAME}Attribute createDefault() {
        return ${ATTRIBUTE_NAME}Attribute.builder()
            .dateTime(FIXED_TIME)
            .build();
    }

    @Override
    protected ${ATTRIBUTE_NAME}Attribute createVariant() {
        return ${ATTRIBUTE_NAME}Attribute.builder()
            .dateTime(FIXED_TIME.plusDays(1))
            .build();
    }
}
EOF
}

generate_xml_test() {
    local ATTRIBUTE_NAME="$1"
    local path="${TEST_JAVA}/codec/xml/${SUB_PATH}/${ATTRIBUTE_NAME}AttributeXmlTest.java"

    if [[ "${DRY_RUN}" == "true" ]]; then
        echo "DRY RUN: would create file: ${path}"
        return 0
    fi

    mkdir -p "${TEST_JAVA}/codec/xml/${SUB_PATH}"
    cat > "${path}" << EOF
package org.purpleBean.kmip.codec.xml.${SUB_PATH};

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.${SUB_PATH}.${ATTRIBUTE_NAME}Attribute;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationSuite;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@DisplayName("${ATTRIBUTE_NAME}Attribute XML Serialization Tests")
class ${ATTRIBUTE_NAME}AttributeXmlTest extends AbstractXmlSerializationSuite<${ATTRIBUTE_NAME}Attribute> {

    private static final OffsetDateTime FIXED_TIME = OffsetDateTime.of(2024, 1, 2, 3, 4, 5, 0, ZoneOffset.UTC);

    @Override
    protected Class<${ATTRIBUTE_NAME}Attribute> type() {
        return ${ATTRIBUTE_NAME}Attribute.class;
    }

    @Override
    protected ${ATTRIBUTE_NAME}Attribute createDefault() {
        return ${ATTRIBUTE_NAME}Attribute.builder()
            .dateTime(FIXED_TIME)
            .build();
    }

    @Override
    protected ${ATTRIBUTE_NAME}Attribute createVariant() {
        return ${ATTRIBUTE_NAME}Attribute.builder()
            .dateTime(FIXED_TIME.plusDays(1))
            .build();
    }
}
EOF
}

generate_ttlv_test() {
    local ATTRIBUTE_NAME="$1"
    local path="${TEST_JAVA}/codec/ttlv/${SUB_PATH}/${ATTRIBUTE_NAME}AttributeTtlvTest.java"

    if [[ "${DRY_RUN}" == "true" ]]; then
        echo "DRY RUN: would create file: ${path}"
        return 0
    fi

    mkdir -p "${TEST_JAVA}/codec/ttlv/${SUB_PATH}"
    cat > "${path}" << EOF
package org.purpleBean.kmip.codec.ttlv.${SUB_PATH};

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.${SUB_PATH}.${ATTRIBUTE_NAME}Attribute;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationSuite;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@DisplayName("${ATTRIBUTE_NAME}Attribute TTLV Serialization Tests")
class ${ATTRIBUTE_NAME}AttributeTtlvTest extends AbstractTtlvSerializationSuite<${ATTRIBUTE_NAME}Attribute> {

    private static final OffsetDateTime FIXED_TIME = OffsetDateTime.of(2024, 1, 2, 3, 4, 5, 0, ZoneOffset.UTC);

    @Override
    protected Class<${ATTRIBUTE_NAME}Attribute> type() {
        return ${ATTRIBUTE_NAME}Attribute.class;
    }

    @Override
    protected ${ATTRIBUTE_NAME}Attribute createDefault() {
        return ${ATTRIBUTE_NAME}Attribute.builder()
            .dateTime(FIXED_TIME)
            .build();
    }

    @Override
    protected ${ATTRIBUTE_NAME}Attribute createVariant() {
        return ${ATTRIBUTE_NAME}Attribute.builder()
            .dateTime(FIXED_TIME.plusDays(1))
            .build();
    }
}
EOF
}

generate_benchmark_subject() {
    local ATTRIBUTE_NAME="$1"
    local path="${TEST_JAVA}/benchmark/subjects/${SUB_PATH}/${ATTRIBUTE_NAME}AttributeBenchmarkSubject.java"

    if [[ "${DRY_RUN}" == "true" ]]; then
        echo "DRY RUN: would create file: ${path}"
        return 0
    fi

    mkdir -p "${TEST_JAVA}/benchmark/subjects/${SUB_PATH}"
    cat > "${path}" << EOF
package org.purpleBean.kmip.benchmark.subjects.${SUB_PATH};

import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.Getter;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.${SUB_PATH}.${ATTRIBUTE_NAME}Attribute;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.benchmark.util.MapperFactory;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;

import java.nio.ByteBuffer;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

public class ${ATTRIBUTE_NAME}AttributeBenchmarkSubject implements KmipBenchmarkSubject {

    private JsonMapper json;
    private XmlMapper xml;
    private TtlvMapper ttlv;

    private ${ATTRIBUTE_NAME}Attribute obj;

    @Getter
    private String jsonStr;
    @Getter
    private String xmlStr;
    @Getter
    private ByteBuffer ttlvBuf;

    public ${ATTRIBUTE_NAME}AttributeBenchmarkSubject() throws Exception {
        this.setup();
    }

    @Override
    public String name() {
        return "${ATTRIBUTE_NAME}Attribute";
    }

    @Override
    public void setup() throws Exception {
        json = MapperFactory.getJsonMapper();
        xml = MapperFactory.getXmlMapper();
        ttlv = MapperFactory.getTtlvMapper();

        // Create test object
        obj = ${ATTRIBUTE_NAME}Attribute.builder()
            .dateTime(OffsetDateTime.of(2024, 1, 2, 3, 4, 5, 0, ZoneOffset.UTC))
            .build();

        // Serialize to all formats for deserialization benchmarks
        jsonStr = json.writeValueAsString(obj);
        xmlStr = xml.writeValueAsString(obj);
        ttlvBuf = ByteBuffer.wrap(ttlv.writeValueAsBytes(obj));
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
        return json.readValue(jsonStr, ${ATTRIBUTE_NAME}Attribute.class);
    }

    @Override
    public String xmlSerialize() throws Exception {
        return xml.writeValueAsString(obj);
    }

    @Override
    public Object xmlDeserialize() throws Exception {
        return xml.readValue(xmlStr, ${ATTRIBUTE_NAME}Attribute.class);
    }

    @Override
    public ByteBuffer ttlvSerialize() throws Exception {
        return ttlv.writeValueAsByteBuffer(obj);
    }

    @Override
    public Object ttlvDeserialize() throws Exception {
        return ttlv.readValue(ttlvBuf.duplicate(), ${ATTRIBUTE_NAME}Attribute.class);
    }
}
EOF
}

#############################################
# Orchestrator for one attribute
#############################################

generate_attribute() {
    local ATTRIBUTE_NAME="$1"
    local ATTRIBUTE_NAME_SNAKE
    ATTRIBUTE_NAME_SNAKE="$(to_snake_upper "$ATTRIBUTE_NAME")"
    local ATTRIBUTE_VAR_NAME
    ATTRIBUTE_VAR_NAME="$(get_attribute_var_name "$ATTRIBUTE_NAME")"

    echo -e "\nGenerating files for ${ATTRIBUTE_NAME}Attribute..."

    $GEN_CLASS       && generate_attribute_class "${ATTRIBUTE_NAME}" "${ATTRIBUTE_NAME_SNAKE}" "${ATTRIBUTE_VAR_NAME}"
    $GEN_JSON_SER    && generate_json_serializer "${ATTRIBUTE_NAME}" "${ATTRIBUTE_NAME_SNAKE}"
    $GEN_JSON_DES    && generate_json_deserializer "${ATTRIBUTE_NAME}" "${ATTRIBUTE_NAME_SNAKE}"
    $GEN_XML_SER     && generate_xml_serializer "${ATTRIBUTE_NAME}" "${ATTRIBUTE_NAME_SNAKE}"
    $GEN_XML_DES     && generate_xml_deserializer "${ATTRIBUTE_NAME}" "${ATTRIBUTE_NAME_SNAKE}"
    $GEN_TTLV_SER    && generate_ttlv_serializer "${ATTRIBUTE_NAME}" "${ATTRIBUTE_NAME_SNAKE}"
    $GEN_TTLV_DES    && generate_ttlv_deserializer "${ATTRIBUTE_NAME}" "${ATTRIBUTE_NAME_SNAKE}"
    $GEN_TEST_JSON   && generate_json_test "${ATTRIBUTE_NAME}"
    $GEN_TEST_XML    && generate_xml_test "${ATTRIBUTE_NAME}"
    $GEN_TEST_TTLV   && generate_ttlv_test "${ATTRIBUTE_NAME}"
    $GEN_BENCHMARK   && generate_benchmark_subject "${ATTRIBUTE_NAME}"

    register_services "${ATTRIBUTE_NAME}" "${SUB_PATH}"

    echo "Generated (or planned) files for ${ATTRIBUTE_NAME}Attribute."
    echo "Note: Don't forget to add the following to your KmipTag.Standard enum:"
    echo "    ${ATTRIBUTE_NAME_SNAKE}(0x\$(printf '%x' \$((RANDOM * 1000 % 65000 + 1000))), \"${ATTRIBUTE_NAME}Attribute\");"
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
            --test-json) GEN_TEST_JSON=true; any_flag=true ;;
            --test-xml) GEN_TEST_XML=true; any_flag=true ;;
            --test-ttlv) GEN_TEST_TTLV=true; any_flag=true ;;
            --benchmark) GEN_BENCHMARK=true; any_flag=true ;;
            --all)
                GEN_CLASS=true; GEN_JSON_SER=true; GEN_JSON_DES=true; GEN_XML_SER=true; GEN_XML_DES=true;
                GEN_TTLV_SER=true; GEN_TTLV_DES=true; GEN_TEST_JSON=true; GEN_TEST_XML=true; GEN_TEST_TTLV=true;
                GEN_BENCHMARK=true; any_flag=true
                ;;
            --help|-h) usage ;;
            --*) echo "Unknown option: $1"; usage ;;
            *) attrs+=("$1") ;;
        esac
        shift
    done

    if [[ ${#attrs[@]} -eq 0 ]]; then
        usage
    fi

    # If no flag provided, perform a dry run and plan to show all artifacts (but do not write)
    if [[ $any_flag == false ]]; then
        DRY_RUN=true
        echo "No generation flags provided -> performing DRY RUN (no files will be written)."
        # Plan to show everything in the dry run
        GEN_CLASS=true
        GEN_JSON_SER=true
        GEN_JSON_DES=true
        GEN_XML_SER=true
        GEN_XML_DES=true
        GEN_TTLV_SER=true
        GEN_TTLV_DES=true
        GEN_TEST_JSON=true
        GEN_TEST_XML=true
        GEN_TEST_TTLV=true
        GEN_BENCHMARK=true
    fi

    # Create directories (this will only print in dry-run mode)
    create_directories "${MAIN_JAVA}" "${TEST_JAVA}" "${SUB_PATH}"

    # Process each attribute name
    for ATTRIBUTE_NAME in "${attrs[@]}"; do
        generate_attribute "${ATTRIBUTE_NAME}"
    done

    if [[ "${DRY_RUN}" == "true" ]]; then
        echo -e "\nDRY RUN complete. Nothing was written."
        echo "Rerun with flags to actually generate files. Example:"
        echo "  ./generate_attribute.sh --all ActivationDate"
    else
        echo -e "\nGeneration complete! Don't forget to:"
        echo "1. Add the attribute tag to the KmipTag.Standard enum"
        echo "2. Update any relevant documentation"
        echo "3. Run the tests to verify everything works as expected"
    fi
}

main "$@
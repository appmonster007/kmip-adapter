#!/bin/bash
set -euo pipefail

# Check if structure names are provided
if [ $# -eq 0 ]; then
    echo "Usage: $0 <StructureName1> [<StructureName2> ...]"
    echo "Example: $0 ProtocolVersion"
    echo "Example: $0 MyNewStructure"
    exit 1
fi

# Store all structure names in an array
STRUCTURE_NAMES=("$@")

# Function to convert string to camel case
get_camel_case() {
    local input="$1"
    # If already in camelCase (first character is lowercase)
    if [[ "$input" =~ ^[a-z] ]]; then
        echo "$input"
        return
    fi
    # Convert from PascalCase to camelCase
    echo "$(tr '[:upper:]' '[:lower:]' <<< ${input:0:1})${input:1}"
}

# Function to convert structure name to snake case
get_structure_snake_case() {
    local name="$1"
    echo "$name" | sed -r 's/([A-Z])/_\1/g' | sed 's/^_//' | tr '[:lower:]' '[:upper:]'
}

# Function to convert string to pascal case
get_pascal_case() {
    local input="$*"
    echo "$input" \
        | sed -E 's/[_-]/ /g' \
        | awk '{for(i=1;i<=NF;i++){ $i=toupper(substr($i,1,1)) tolower(substr($i,2)) }}1' \
        | tr -d ' '
}

# Function to add an entry to a service file
add_service_entry() {
    local file="$1"
    local entry="$2"
    
    # Create the service file if it doesn't exist
    mkdir -p "$(dirname "${file}")"
    touch "${file}"
    
    # Check if the entry already exists
    if ! grep -q "^${entry}" "${file}"; then
        echo "${entry}" >> "${file}"
        echo "Added service entry: ${entry} to ${file}"
    fi

    local temp_file="${file}.tmp"
        sort -u "$file" | grep -v '^[[:space:]]*$' > "$temp_file"

        # Only update the file if it changed to preserve timestamps
        if ! cmp -s "$file" "$temp_file"; then
            mv "$temp_file" "$file"
        else
            rm -f "$temp_file"
        fi
}

# ===== Helper Functions =====

# Function to create all required directories
create_directories() {
    local main_java="$1" test_java="$2" sub_path="$3"

    echo "Creating directory structure..."

    # Main source and codec directories
    # Create base directories
    mkdir -p "${main_java}/${sub_path}"
    
    # Create codec directories
    for dir in serializer deserializer; do
        mkdir -p "${main_java}/codec/json/${dir}/kmip/${sub_path}"
        mkdir -p "${main_java}/codec/xml/${dir}/kmip/${sub_path}"
        mkdir -p "${main_java}/codec/ttlv/${dir}/kmip/${sub_path}"
    done
    
    # Create test directories
    mkdir -p "${test_java}/${sub_path}"
    for format in json xml ttlv; do
        mkdir -p "${test_java}/codec/${format}/${sub_path}"
    done
    
    # Create benchmark and resources directories
    mkdir -p "${test_java}/benchmark/subjects/${sub_path}"
    mkdir -p "src/main/resources/META-INF/services"
    mkdir -p "src/test/resources/META-INF/services"

    echo "✓ Directory structure created"
}

# Base directories
BASE_DIR="$(pwd)"
MAIN_JAVA="src/main/java/org/purpleBean/kmip"
TEST_JAVA="src/test/java/org/purpleBean/kmip"
SUB_PATH="common/structure"

# Function to generate files for a single structure
generate_structure() {
    local STRUCTURE_NAME="$1"
    local STRUCTURE_VAR_NAME=$(get_camel_case "$STRUCTURE_NAME")
    local STRUCTURE_NAME_SNAKE=$(get_structure_snake_case "$STRUCTURE_NAME")
    local STRUCTURE_NAME_LOWERCASE=$(echo "$STRUCTURE_NAME" | tr '[:upper:]' '[:lower:]')
    
    echo -e "\nGenerating files for ${STRUCTURE_NAME}..."
    
    echo -e "\n=== Generating ${STRUCTURE_NAME} structure ==="
    
    # 1. Create the main structure class
    echo "- Generating main structure class..."
    cat > "${MAIN_JAVA}/${SUB_PATH}/${STRUCTURE_NAME}.java" << EOF
package org.purpleBean.kmip.${SUB_PATH//\//.};

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

    private final KmipTag kmipTag = new KmipTag(KmipTag.Standard.${STRUCTURE_NAME_SNAKE});
    private final EncodingType encodingType = EncodingType.STRUCTURE;
    private final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2);
    
    // TODO: Add your structure fields here
    // Example:
    @NonNull
    private final ActivationDateAttribute activationDate;
    private final State state;

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

    /**
     * Builder for ${STRUCTURE_NAME}.
     */
    public static class ${STRUCTURE_NAME}Builder {
        /**
         * Build a new ${STRUCTURE_NAME} instance with the current configuration.
         */
        public ${STRUCTURE_NAME} build() {
            // Validate required fields
            validate();
            
            // Create a copy to ensure immutability
            // ${STRUCTURE_NAME} result = new ${STRUCTURE_NAME}();
            // TODO: Copy all fields from instance to result
            // result.activationDate = this.activationDate;
            // result.state = this.state;

            return new ${STRUCTURE_NAME}(activationDate, state);
        }
        
        /**
         * Validate the current configuration.
         */
        private void validate() {
            List<KmipDataType> fields = new ArrayList<>();
            fields.add(activationDate);
            fields.add(state);

            KmipSpec spec = KmipContext.getSpec();
            
            // Validate KMIP spec compatibility
            for (KmipDataType field : fields) {
                if (field != null && !field.isSupportedFor(spec)) {
                    throw new IllegalArgumentException(
                        String.format("%s is not supported for KMIP spec %s",
                            field.getKmipTag().getDescription(), spec)
                    );
                }
            }
            
            // Validate required fields
            // if (activationDate == null) {
            //     throw new IllegalArgumentException("ActivationDate is required");
            // }
        }
    }
}
EOF

    echo "✓ Main structure class generated"
    
    # 2. Create JSON Serializer
    echo "- Generating JSON serializer..."
    cat > "${MAIN_JAVA}/codec/json/serializer/kmip/${SUB_PATH}/${STRUCTURE_NAME}JsonSerializer.java" << EOF
package org.purpleBean.kmip.codec.json.serializer.kmip.${SUB_PATH//\//.};

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.common.*;
import org.purpleBean.kmip.common.enumeration.*;
import org.purpleBean.kmip.common.structure.*;
import org.purpleBean.kmip.codec.json.serializer.kmip.KmipDataTypeJsonSerializer;
import org.purpleBean.kmip.${SUB_PATH//\//.}.${STRUCTURE_NAME};

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

public class ${STRUCTURE_NAME}JsonSerializer extends KmipDataTypeJsonSerializer<${STRUCTURE_NAME}> {

    @Override
    public void serialize(${STRUCTURE_NAME} ${STRUCTURE_VAR_NAME}, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        // Validation: Null check
        if (${STRUCTURE_VAR_NAME} == null) {
            return;
        }

        // Validation: KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();
        if (!${STRUCTURE_VAR_NAME}.isSupportedFor(spec)) {
            throw new UnsupportedEncodingException(
                String.format("%s is not supported for KMIP spec %s", 
                    ${STRUCTURE_VAR_NAME}.getKmipTag().getDescription(), spec)
            );
        }

        List<KmipDataType> fields = ${STRUCTURE_VAR_NAME}.getValues();
        // Validation: Field compatibility with KMIP spec
        for (KmipDataType field : fields) {
            if (field != null && !field.isSupportedFor(spec)) {
                throw new UnsupportedEncodingException(
                    String.format("%s in %s is not supported for KMIP spec %s", 
                        field.getKmipTag().getDescription(), 
                        ${STRUCTURE_VAR_NAME}.getKmipTag().getDescription(), 
                        spec)
                );
            }
        }

        jsonGenerator.writeStartObject();
        jsonGenerator.writeObject(${STRUCTURE_VAR_NAME}.getKmipTag());
        jsonGenerator.writeStringField("type", ${STRUCTURE_VAR_NAME}.getEncodingType().getDescription());
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

    # 3. Create JSON Deserializer
    cat > "${MAIN_JAVA}/codec/json/deserializer/kmip/${SUB_PATH}/${STRUCTURE_NAME}JsonDeserializer.java" << EOF
package org.purpleBean.kmip.codec.json.deserializer.kmip.${SUB_PATH//\//.};

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.common.*;
import org.purpleBean.kmip.common.enumeration.*;
import org.purpleBean.kmip.common.structure.*;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.${SUB_PATH//\//.}.${STRUCTURE_NAME};

import java.io.IOException;
import java.util.NoSuchElementException;

public class ${STRUCTURE_NAME}JsonDeserializer extends KmipDataTypeJsonDeserializer<${STRUCTURE_NAME}> {
    private final KmipTag kmipTag = new KmipTag(KmipTag.Standard.${STRUCTURE_NAME_SNAKE});
    private final EncodingType encodingType = EncodingType.STRUCTURE;

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
            if (!valueNode.has("tag")) continue;
            
            KmipTag.Value nodeTag = p.getCodec().treeToValue(valueNode, KmipTag.class).getValue();
            setValue(builder, nodeTag, valueNode, p, ctxt);
        }

        ${STRUCTURE_NAME} ${STRUCTURE_VAR_NAME} = builder.build();

        // Validate KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();
        if (!${STRUCTURE_VAR_NAME}.isSupportedFor(spec)) {
            throw new NoSuchElementException(
                String.format("${STRUCTURE_NAME} is not supported for KMIP spec %s", spec)
            );
        }

        return ${STRUCTURE_VAR_NAME};
    }

    /**
     * Sets the appropriate field in the builder based on the tag and value.
     *
     * @param builder the builder to set the field on
     * @param nodeTag the tag identifying the field to set
     * @param node the JSON node containing the field value
     * @param p the JsonParser
     * @param ctxt the DeserializationContext
     * @throws IOException if there is an error deserializing the value
     */
    private void setValue(${STRUCTURE_NAME}.${STRUCTURE_NAME}Builder builder,
                          KmipTag.Value nodeTag,
                          JsonNode node,
                          JsonParser p,
                          DeserializationContext ctxt) throws IOException {
        // TODO: Implement field deserialization based on tag, preferably using switch case expression
        // Example:
        switch (nodeTag) {
            case KmipTag.Standard.ACTIVATION_DATE ->
                    builder.activationDate(p.getCodec().treeToValue(node, ActivationDateAttribute.class));
            case KmipTag.Standard.STATE -> builder.state(p.getCodec().treeToValue(node, State.class));
            default -> throw new IllegalArgumentException();
        }
    }
}
EOF

    # 4. Create XML Serializer
    cat > "${MAIN_JAVA}/codec/xml/serializer/kmip/${SUB_PATH}/${STRUCTURE_NAME}XmlSerializer.java" << EOF
package org.purpleBean.kmip.codec.xml.serializer.kmip.${SUB_PATH//\//.};

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.common.*;
import org.purpleBean.kmip.common.enumeration.*;
import org.purpleBean.kmip.common.structure.*;
import org.purpleBean.kmip.codec.xml.serializer.kmip.KmipDataTypeXmlSerializer;
import org.purpleBean.kmip.${SUB_PATH//\//.}.${STRUCTURE_NAME};

import javax.xml.namespace.QName;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

public class ${STRUCTURE_NAME}XmlSerializer extends KmipDataTypeXmlSerializer<${STRUCTURE_NAME}> {

    @Override
    public void serialize(${STRUCTURE_NAME} ${STRUCTURE_VAR_NAME}, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        // Validation: KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();
        if (!${STRUCTURE_VAR_NAME}.isSupportedFor(spec)) {
            throw new UnsupportedEncodingException(
                String.format("%s not supported for KMIP spec %s", 
                    ${STRUCTURE_VAR_NAME}.getClass().getSimpleName(), spec)
            );
        }

        if (!(gen instanceof ToXmlGenerator xmlGen)) {
            throw new IllegalStateException("Expected ToXmlGenerator");
        }

        // Start element with name from kmipTag
        String elementName = ${STRUCTURE_VAR_NAME}.getKmipTag().getDescription();
        xmlGen.setNextName(QName.valueOf(elementName));
        xmlGen.writeStartObject(${STRUCTURE_VAR_NAME});

        // Serialize all fields
        List<KmipDataType> values = ${STRUCTURE_VAR_NAME}.getValues();
        for (KmipDataType kmipDataType : values) {
            if (kmipDataType != null && kmipDataType.getKmipTag() != null) {
                serializers.defaultSerializeField(
                    kmipDataType.getKmipTag().getDescription(),
                    kmipDataType,
                    gen
                );
            }
        }
        
        xmlGen.writeEndObject();
    }
}
EOF

    # 5. Create XML Deserializer
    cat > "${MAIN_JAVA}/codec/xml/deserializer/kmip/${SUB_PATH}/${STRUCTURE_NAME}XmlDeserializer.java" << EOF
package org.purpleBean.kmip.codec.xml.deserializer.kmip.${SUB_PATH//\//.};

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
import org.purpleBean.kmip.${SUB_PATH//\//.}.${STRUCTURE_NAME};

import java.io.IOException;
import java.util.Map;

public class ${STRUCTURE_NAME}XmlDeserializer extends KmipDataTypeXmlDeserializer<${STRUCTURE_NAME}> {
    private final KmipTag kmipTag = new KmipTag(KmipTag.Standard.${STRUCTURE_NAME_SNAKE});
    private final EncodingType encodingType = EncodingType.STRUCTURE;

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

        ${STRUCTURE_NAME} ${STRUCTURE_VAR_NAME} = builder.build();

        if (!${STRUCTURE_VAR_NAME}.isSupportedFor(spec)) {
            ctxt.reportInputMismatch(${STRUCTURE_NAME}.class, 
                "${STRUCTURE_NAME} not supported for spec " + spec);
            return null;
        }

        return ${STRUCTURE_VAR_NAME};
    }

    /**
     * Sets the appropriate field in the builder based on the tag and value.
     *
     * @param builder the builder to set the field on
     * @param nodeTag the tag identifying the field to set
     * @param node the JSON node containing the field value
     * @param p the JsonParser
     * @param ctxt the DeserializationContext
     * @throws IOException if there is an error deserializing the value
     */
    private void setValue(${STRUCTURE_NAME}.${STRUCTURE_NAME}Builder builder,
                          KmipTag.Value nodeTag,
                          JsonNode node,
                          JsonParser p,
                          DeserializationContext ctxt) throws IOException {
        // TODO: Implement field deserialization based on nodeTag
        // Example:
        switch (nodeTag) {
            case KmipTag.Standard.ACTIVATION_DATE ->
                    builder.activationDate(p.getCodec().treeToValue(node, ActivationDateAttribute.class));
            case KmipTag.Standard.STATE -> builder.state(p.getCodec().treeToValue(node, State.class));
            default -> throw new IllegalArgumentException();
        }
    }
}
EOF

    # 6. Create TTLV Serializer
    echo "- Generating TTLV serializer..."
    cat > "${MAIN_JAVA}/codec/ttlv/serializer/kmip/${SUB_PATH}/${STRUCTURE_NAME}TtlvSerializer.java" << EOF
package org.purpleBean.kmip.codec.ttlv.serializer.kmip.${SUB_PATH//\//.};

import org.purpleBean.kmip.*;
import org.purpleBean.kmip.common.*;
import org.purpleBean.kmip.common.enumeration.*;
import org.purpleBean.kmip.common.structure.*;
import org.purpleBean.kmip.codec.ttlv.TtlvObject;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.codec.ttlv.serializer.kmip.KmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.${SUB_PATH//\//.}.${STRUCTURE_NAME};

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
            throw new UnsupportedEncodingException(
                String.format("%s not supported for KMIP spec %s", 
                    value.getClass().getSimpleName(), spec)
            );
        }

        List<KmipDataType> nestedValues = value.getValues();
        byte[] tag = value.getKmipTag().getTagBytes();
        byte type = value.getEncodingType().getTypeValue();

        List<ByteBuffer> nestedObjects = new ArrayList<>();
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

    # 7. Create TTLV Deserializer
    echo "- Generating TTLV deserializer..."
    cat > "${MAIN_JAVA}/codec/ttlv/deserializer/kmip/${SUB_PATH}/${STRUCTURE_NAME}TtlvDeserializer.java" << EOF
package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.${SUB_PATH//\//.};

import org.purpleBean.kmip.*;
import org.purpleBean.kmip.common.*;
import org.purpleBean.kmip.common.enumeration.*;
import org.purpleBean.kmip.common.structure.*;
import org.purpleBean.kmip.codec.ttlv.TtlvObject;
import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.KmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.${SUB_PATH//\//.}.${STRUCTURE_NAME};

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

public class ${STRUCTURE_NAME}TtlvDeserializer extends KmipDataTypeTtlvDeserializer<${STRUCTURE_NAME}> {
    private final EncodingType type = EncodingType.STRUCTURE;
    private final KmipTag kmipTag = new KmipTag(KmipTag.Standard.${STRUCTURE_NAME_SNAKE});

    @Override
    public ${STRUCTURE_NAME} deserialize(ByteBuffer ttlvBuffer, TtlvMapper mapper) throws IOException {
        TtlvObject obj = TtlvObject.fromBuffer(ttlvBuffer);
        if (Arrays.equals(obj.getTag(), kmipTag.getTagBytes())
                && obj.getType() != type.getTypeValue()) {
            throw new IllegalArgumentException(
                String.format("Expected %s type for %s, got %s", 
                    type.getTypeValue(), 
                    kmipTag.getDescription(),
                    obj.getType())
            );
        }

        List<TtlvObject> nestedObjects = TtlvObject.fromBytesMultiple(obj.getValue());
        KmipSpec spec = KmipContext.getSpec();
        ${STRUCTURE_NAME}.${STRUCTURE_NAME}Builder builder = ${STRUCTURE_NAME}.builder();

        for (TtlvObject ttlvObject : nestedObjects) {
            KmipTag.Value nodeTag = KmipTag.fromBytes(spec, ttlvObject.getTag());
            setValue(builder, nodeTag, ttlvObject, mapper);
        }

        ${STRUCTURE_NAME} ${STRUCTURE_VAR_NAME} = builder.build();

        if (!${STRUCTURE_VAR_NAME}.isSupportedFor(spec)) {
            throw new NoSuchElementException(
                String.format("%s is not supported for KMIP spec %s",
                    ${STRUCTURE_VAR_NAME}.getClass().getSimpleName(), spec)
            );
        }
        return ${STRUCTURE_VAR_NAME};
    }

    private void setValue(${STRUCTURE_NAME}.${STRUCTURE_NAME}Builder builder,
                          KmipTag.Value nodeTag, 
                          TtlvObject ttlvObject, 
                          TtlvMapper mapper) throws IOException {
        // TODO: Implement field deserialization based on nodeTag
        // Example:
        switch (nodeTag) {
            case KmipTag.Standard.ACTIVATION_DATE ->
                    builder.activationDate(mapper.readValue(ttlvObject.toByteBuffer(), ActivationDateAttribute.class));
            case KmipTag.Standard.STATE -> builder.state(mapper.readValue(ttlvObject.toByteBuffer(), State.class));
            default -> throw new IllegalArgumentException();
        }
    }
}
EOF



    # 8. Create Domain Test
    echo "- Generating domain test..."
    mkdir -p "${TEST_JAVA}/${SUB_PATH}"
    cat > "${TEST_JAVA}/${SUB_PATH}/${STRUCTURE_NAME}Test.java" << EOF
package org.purpleBean.kmip.${SUB_PATH//\//.};

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipDataType;
import org.purpleBean.kmip.common.ActivationDateAttribute;
import org.purpleBean.kmip.common.enumeration.State;
import org.purpleBean.kmip.test.suite.AbstractKmipStructureSuite;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

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
        ActivationDateAttribute activationDate = ActivationDateAttribute.builder().dateTime(FIXED_TIME).build();
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
        // TODO: Add validation for each component
        // Example:
        // assertThat(values.get(0).getEncodingType()).isEqualTo(EncodingType.DATE_TIME);
        // assertThat(values.get(1).getEncodingType()).isEqualTo(EncodingType.ENUMERATION);
    }
}
EOF

    # 9. Generate Serialization Tests
    generate_serialization_test() {
        local format=$1
        local format_upper=$(echo "$format" | tr '[:lower:]' '[:upper:]')
        local format_lower=$(echo "$format" | tr '[:upper:]' '[:lower:]')
        local format_pascal=$(get_pascal_case "$format")
        local suite_name="${STRUCTURE_NAME}${format_pascal}Test"
        local base_suite="Abstract${format_pascal}SerializationSuite"
        
        echo "- Generating ${format_upper} test..."
        mkdir -p "${TEST_JAVA}/codec/${format_lower}/${SUB_PATH}"
        
        cat > "${TEST_JAVA}/codec/${format_lower}/${SUB_PATH}/${suite_name}.java" << EOF
package org.purpleBean.kmip.codec.${format_lower}.${SUB_PATH//\//.};

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.${SUB_PATH//\//.}.${STRUCTURE_NAME};
import org.purpleBean.kmip.test.suite.${base_suite};
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.common.*;
import org.purpleBean.kmip.common.enumeration.*;
import org.purpleBean.kmip.common.structure.*;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@DisplayName("${STRUCTURE_NAME} ${format_upper} Serialization Tests")
class ${suite_name} extends ${base_suite}<${STRUCTURE_NAME}> {

    private static final OffsetDateTime FIXED_TIME = OffsetDateTime.of(2024, 1, 2, 3, 4, 5, 0, ZoneOffset.UTC);

    @Override
    protected Class<${STRUCTURE_NAME}> type() {
        return ${STRUCTURE_NAME}.class;
    }

    @Override
    protected ${STRUCTURE_NAME} createDefault() {
        // TODO: Update with actual default values for your structure
        ActivationDateAttribute activationDate = ActivationDateAttribute.builder().dateTime(FIXED_TIME).build();
        State state = new State(State.Standard.ACTIVE);
        return ${STRUCTURE_NAME}.builder()
            .activationDate(activationDate)
            .state(state)
            .build();
    }

    @Override
    protected ${STRUCTURE_NAME} createVariant() {
        // TODO: Update with different values to test variations
        ActivationDateAttribute activationDate = ActivationDateAttribute.builder().dateTime(FIXED_TIME.plusDays(1)).build();
        State state = new State(State.Standard.DEACTIVATED);
        return ${STRUCTURE_NAME}.builder()
            .activationDate(activationDate)
            .state(state)
            .build();
    }
}
EOF
    }

    # Generate all serialization tests
    generate_serialization_test "json"
    generate_serialization_test "xml"
    generate_serialization_test "ttlv"

    # 10. Create Benchmark Subject
    echo "- Generating benchmark subject..."
    local bench_dir="${TEST_JAVA}/benchmark/subjects/${SUB_PATH}"
    mkdir -p "${bench_dir}"
    
    cat > "${bench_dir}/${STRUCTURE_NAME}BenchmarkSubject.java" << EOF
package org.purpleBean.kmip.benchmark.subjects.${SUB_PATH//\//.};

import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.Getter;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.common.*;
import org.purpleBean.kmip.common.enumeration.*;
import org.purpleBean.kmip.common.structure.*;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.benchmark.util.MapperFactory;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.${SUB_PATH//\//.}.${STRUCTURE_NAME};

import java.nio.ByteBuffer;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

public class ${STRUCTURE_NAME}BenchmarkSubject implements KmipBenchmarkSubject {

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
        // Configure mappers
        json = MapperFactory.getJsonMapper();
        xml = MapperFactory.getXmlMapper();
        ttlv = MapperFactory.getTtlvMapper();

        // Create test object with sample data
        var fixed = OffsetDateTime.of(2024, 1, 2, 3, 4, 5, 0, ZoneOffset.UTC);
        ActivationDateAttribute activationDate = ActivationDateAttribute.builder().dateTime(fixed).build();
        State state = new State(State.Standard.ACTIVE);
        obj = ${STRUCTURE_NAME}.builder()
            .activationDate(activationDate)
            .state(state)
            .build();

        // Pre-serialize for benchmarks
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

    # Add service entries for the structure
    echo "- Registering service entries..."
    
    # JSON Serializer
    add_service_entry "src/main/resources/META-INF/services/org.purpleBean.kmip.codec.json.serializer.kmip.KmipDataTypeJsonSerializer" \
        "org.purpleBean.kmip.codec.json.serializer.kmip.${SUB_PATH//\//.}.${STRUCTURE_NAME}JsonSerializer"
    
    # JSON Deserializer
    add_service_entry "src/main/resources/META-INF/services/org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer" \
        "org.purpleBean.kmip.codec.json.deserializer.kmip.${SUB_PATH//\//.}.${STRUCTURE_NAME}JsonDeserializer"
    
    # XML Serializer
    add_service_entry "src/main/resources/META-INF/services/org.purpleBean.kmip.codec.xml.serializer.kmip.KmipDataTypeXmlSerializer" \
        "org.purpleBean.kmip.codec.xml.serializer.kmip.${SUB_PATH//\//.}.${STRUCTURE_NAME}XmlSerializer"
    
    # XML Deserializer
    add_service_entry "src/main/resources/META-INF/services/org.purpleBean.kmip.codec.xml.deserializer.kmip.KmipDataTypeXmlDeserializer" \
        "org.purpleBean.kmip.codec.xml.deserializer.kmip.${SUB_PATH//\//.}.${STRUCTURE_NAME}XmlDeserializer"
    
    # TTLV Serializer
    add_service_entry "src/main/resources/META-INF/services/org.purpleBean.kmip.codec.ttlv.serializer.kmip.KmipDataTypeTtlvSerializer" \
        "org.purpleBean.kmip.codec.ttlv.serializer.kmip.${SUB_PATH//\//.}.${STRUCTURE_NAME}TtlvSerializer"
    
    # TTLV Deserializer
    add_service_entry "src/main/resources/META-INF/services/org.purpleBean.kmip.codec.ttlv.deserializer.kmip.KmipDataTypeTtlvDeserializer" \
        "org.purpleBean.kmip.codec.ttlv.deserializer.kmip.${SUB_PATH//\//.}.${STRUCTURE_NAME}TtlvDeserializer"
    
    # Benchmark Subject
    add_service_entry "src/test/resources/META-INF/services/org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject" \
        "org.purpleBean.kmip.benchmark.subjects.${SUB_PATH//\//.}.${STRUCTURE_NAME}BenchmarkSubject"
    
    echo "✓ Successfully generated and registered all files for ${STRUCTURE_NAME}"
    echo ""
}

# Create directories once
create_directories "${MAIN_JAVA}" "${TEST_JAVA}" "${SUB_PATH}"

# Process each structure name
for structure_name in "${STRUCTURE_NAMES[@]}"; do
    generate_structure "$structure_name"
done

echo "========================================"
echo "✅ Successfully generated all files for ${#STRUCTURE_NAMES[@]} structure(s)"
echo ""
echo "Next steps:"
echo "1. Update the fields in ${STRUCTURE_NAMES[0]}.java with your structure's attributes"
echo "2. Implement the serialization/deserialization logic in the respective codec classes"
echo "3. Add validation rules and business logic as needed"
echo "4. Run tests to verify the implementation"
echo ""
echo "Generation complete! 🚀"

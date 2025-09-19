#!/bin/bash

# Check if structure names are provided
if [ $# -eq 0 ]; then
    echo "Usage: $0 <AttributeStructureName1> [<AttributeStructureName2> ...]"
    echo "Example: $0 CustomAttribute"
    echo "Example: $0 SecurityAttribute"
    exit 1
fi

# Store all structure names in an array
STRUCTURE_NAMES=("$@")

# Function to convert to camelCase
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

# Function to add and sort service file entries
add_service_entry() {
    local file="$1"
    local entry="$2"
    
    # Create parent directory if it doesn't exist
    mkdir -p "$(dirname "$file")"
    
    # Create file if it doesn't exist
    touch "$file"
    
    # Check if entry already exists
    if ! grep -qFx "$entry" "$file"; then
        # Add the entry
        echo "$entry" >> "$file"
        
        # Sort the file and remove duplicates
        sort -u "$file" -o "$file"
        
        echo "Added service entry: $entry to $file"
    else
        echo "Service entry already exists: $entry in $file"
    fi
}

# Function to convert structure name to snake case
get_structure_snake_case() {
    local name="$1"
    echo "$name" | sed -r 's/([A-Z])/_\1/g' | sed 's/^_//' | tr '[:lower:]' '[:upper:]'
}

# Function to convert string to pascal case
get_pascal_case() {
    local input="$*"
    echo "$input" | sed -E 's/[_-]/ /g' | awk '{for(i=1;i<=NF;i++){ $i=toupper(substr($i,1,1)) tolower(substr($i,2)) }}1' | tr -d ' '
}

# Function to create all required directories
create_directories() {
    local main_java="$1" test_java="$2" sub_path="$3"

    echo "Creating directory structure..."

    # Main source and codec directories
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

# Function to generate files for a single attribute structure
generate_attribute_structure() {
    local STRUCTURE_NAME="$1"
    local STRUCTURE_VAR_NAME=$(get_camel_case "$STRUCTURE_NAME")
    local STRUCTURE_NAME_SNAKE=$(get_structure_snake_case "$STRUCTURE_NAME")
    local PACKAGE_PATH="${SUB_PATH}"
    local PACKAGE_DOT="${PACKAGE_PATH//\//.}"
    
    echo "\nGenerating files for ${STRUCTURE_NAME}..."
    
    # 1. Create domain class
    generate_domain_class "$STRUCTURE_NAME" "$PACKAGE_PATH"

    # 2. Create test class
    generate_domain_test "$STRUCTURE_NAME" "$PACKAGE_PATH"

    # 3. Create JSON serializer and deserializer
    generate_json_serializer "$STRUCTURE_NAME" "$PACKAGE_PATH"
    generate_json_deserializer "$STRUCTURE_NAME" "$PACKAGE_PATH"

    # 4. Create XML serializer and deserializer
    generate_xml_serializer "$STRUCTURE_NAME" "$PACKAGE_PATH"
    generate_xml_deserializer "$STRUCTURE_NAME" "$PACKAGE_PATH"

    # 5. Create TTLV serializer and deserializer
    generate_ttlv_serializer "$STRUCTURE_NAME" "$PACKAGE_PATH"
    generate_ttlv_deserializer "$STRUCTURE_NAME" "$PACKAGE_PATH"

    # 6. Create serialization tests
    generate_serialization_test "json"
    generate_serialization_test "xml"
    generate_serialization_test "ttlv"
    
    # 7. Generate benchmark subject
    generate_benchmark_subject "$STRUCTURE_NAME" "$PACKAGE_PATH"
    
    # 8. Register services
    
    # Add to JSON serializers
    add_service_entry "src/main/resources/META-INF/services/org.purpleBean.kmip.codec.json.serializer.kmip.KmipDataTypeJsonSerializer" \
        "org.purpleBean.kmip.codec.json.serializer.kmip.${PACKAGE_DOT}.${STRUCTURE_NAME}JsonSerializer"
    
    # Add to JSON deserializers
    add_service_entry "src/main/resources/META-INF/services/org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer" \
        "org.purpleBean.kmip.codec.json.deserializer.kmip.${PACKAGE_DOT}.${STRUCTURE_NAME}JsonDeserializer"
    
    # Add to XML serializers
    add_service_entry "src/main/resources/META-INF/services/org.purpleBean.kmip.codec.xml.serializer.kmip.KmipDataTypeXmlSerializer" \
        "org.purpleBean.kmip.codec.xml.serializer.kmip.${PACKAGE_DOT}.${STRUCTURE_NAME}XmlSerializer"
    
    # Add to XML deserializers
    add_service_entry "src/main/resources/META-INF/services/org.purpleBean.kmip.codec.xml.deserializer.kmip.KmipDataTypeXmlDeserializer" \
        "org.purpleBean.kmip.codec.xml.deserializer.kmip.${PACKAGE_DOT}.${STRUCTURE_NAME}XmlDeserializer"
    
    # Add to TTLV serializers
    add_service_entry "src/main/resources/META-INF/services/org.purpleBean.kmip.codec.ttlv.serializer.kmip.KmipDataTypeTtlvSerializer" \
        "org.purpleBean.kmip.codec.ttlv.serializer.kmip.${PACKAGE_DOT}.${STRUCTURE_NAME}TtlvSerializer"
    
    # Add to TTLV deserializers
    add_service_entry "src/main/resources/META-INF/services/org.purpleBean.kmip.codec.ttlv.deserializer.kmip.KmipDataTypeTtlvDeserializer" \
        "org.purpleBean.kmip.codec.ttlv.deserializer.kmip.${PACKAGE_DOT}.${STRUCTURE_NAME}TtlvDeserializer"
    
    # Add to benchmark subjects
    add_service_entry "src/test/resources/META-INF/services/org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject" \
        "org.purpleBean.kmip.benchmark.subjects.${PACKAGE_DOT}.${STRUCTURE_NAME}BenchmarkSubject"
    
    echo "Generated files for ${STRUCTURE_NAME} in ${BASE_DIR}"
}

generate_domain_test() {
    local class_name=$1
    echo $class_name
    local package_path=$2
    local class_name_snake=$(echo "$class_name" | sed -r 's/([a-z0-9])([A-Z])/\1_\2/g' | tr '[:lower:]' '[:upper:]')

    echo "- Generating test class..."
    mkdir -p "src/test/java/org/purpleBean/kmip/${package_path}/"

    cat > "src/test/java/org/purpleBean/kmip/${package_path}/${class_name}Test.java" <<EOF
package org.purpleBean.kmip.${package_path//\//.};

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.common.*;
import org.purpleBean.kmip.common.enumeration.*;
import org.purpleBean.kmip.common.structure.*;
import org.purpleBean.kmip.test.suite.AbstractKmipAttributeStructureSuite;

import java.time.OffsetDateTime;
import static org.assertj.core.api.Assertions.*;

@DisplayName("${class_name} Tests")
class ${class_name}Test extends AbstractKmipAttributeStructureSuite<${class_name}> {

    @Override
    protected Class<${class_name}> type() {
        return ${class_name}.class;
    }

    @Override
    protected ${class_name} createDefault() {
        return ${class_name}.builder()
            // TODO: Set default values for testing
            .activationDate(ActivationDateAttribute.builder().dateTime(OffsetDateTime.now()).build())
            .build();
    }

    @Override
    protected boolean expectAlwaysPresent() {
        return false; // TODO: Set expected value
    }

    @Override
    protected boolean expectServerInitializable() {
        return false; // TODO: Set expected value
    }

    @Override
    protected boolean expectClientInitializable() {
        return false; // TODO: Set expected value
    }

    @Override
    protected boolean expectClientDeletable() {
        return false; // TODO: Set expected value
    }

    @Override
    protected boolean expectMultiInstanceAllowed() {
        return false; // TODO: Set expected value
    }

    @Override
    protected State stateForServerModifiableTrue() {
        return new State(State.Standard.ACTIVE); // TODO: Return appropriate state
    }

    @Override
    protected State stateForServerModifiableFalse() {
        return new State(State.Standard.DEACTIVATED); // TODO: Return appropriate state
    }

    @Override
    protected State stateForClientModifiableTrue() {
        return new State(State.Standard.ACTIVE); // TODO: Return appropriate state
    }

    @Override
    protected State stateForClientModifiableFalse() {
        return new State(State.Standard.DEACTIVATED); // TODO: Return appropriate state
    }

    @Test
    @DisplayName("should have correct KMIP tag")
    void shouldHaveCorrectKmipTag() {
        ${class_name} attr = createDefault();
        assertThat(attr.getKmipTag().getValue()).isEqualTo(KmipTag.Standard.${class_name_snake});
    }

    // TODO: Add more specific tests
}
EOF
}

generate_domain_class() {
    local class_name=$1
    local package_path=$2
    local class_name_snake=$(echo "$class_name" | sed -r 's/([a-z0-9])([A-Z])/\1_\2/g' | tr '[:lower:]' '[:upper:]')
    
    echo "- Generating domain class..."
    mkdir -p "src/main/java/org/purpleBean/kmip/${package_path}/"
    
    cat > "src/main/java/org/purpleBean/kmip/${package_path}/${class_name}.java" <<EOF
package org.purpleBean.kmip.${package_path//\//.};

import lombok.*;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.common.*;
import org.purpleBean.kmip.common.enumeration.*;
import org.purpleBean.kmip.KmipAttribute;
import org.purpleBean.kmip.KmipStructure;

import java.util.*;

/**
 * KMIP ${class_name} structure that implements both KmipAttribute and KmipStructure.
 */
@Data
@Builder
public class ${class_name} implements KmipStructure, KmipAttribute {

    private final KmipTag kmipTag = new KmipTag(KmipTag.Standard.${class_name_snake});
    private final EncodingType encodingType = EncodingType.STRUCTURE;
    private final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2);
    
    // Attribute properties with defaults
    private final boolean alwaysPresent = false;
    private final boolean serverInitializable = false;
    private final boolean clientInitializable = false;
    private final boolean clientDeletable = false;
    private final boolean multiInstanceAllowed = false;
    
    // TODO: Add your structure fields here
    // Example:
    @NonNull
    private final ActivationDateAttribute activationDate;
    private final State state;
    
    @Override
    public List<KmipDataType> getValues() {
        List<KmipDataType> values = new ArrayList<>();
        // TODO: Add all fields to values list
        // Example:
        values.add(activationDate);
        if(state != null) {
            values.add(state);
        }
        return values;
    }
    
    @Override
    public boolean isSupportedFor(KmipSpec spec) {
        return supportedVersions.contains(spec);
    }
    
    @Override
    public boolean isAlwaysPresent() {
        return alwaysPresent;
    }
    
    @Override
    public boolean isServerInitializable() {
        return serverInitializable;
    }
    
    @Override
    public boolean isClientInitializable() {
        return clientInitializable;
    }
    
    @Override
    public boolean isClientDeletable() {
        return clientDeletable;
    }
    
    @Override
    public boolean isMultiInstanceAllowed() {
        return multiInstanceAllowed;
    }
    
    @Override
    public boolean isServerModifiable(State state) {
        // TODO: Implement server modifiable logic based on state
        return state.getValue().getValue() == State.Standard.ACTIVE.getValue();
    }
    
    @Override
    public boolean isClientModifiable(State state) {
        // TODO: Implement client modifiable logic based on state
        return state.getValue().getValue() == State.Standard.ACTIVE.getValue();
    }
    
    /**
     * Builder class with validation.
     */
    public static class ${class_name}Builder {
        
        /**
         * Builds and validates the ${class_name} instance.
         */
        public ${class_name} build() {
            validate();
            return new ${class_name}(/* TODO: Add constructor parameters */);
        }
        
        /**
         * Validate the current configuration.
         */
        private void validate() {
            // Validate required fields
            // if (field == null) {
            //     throw new IllegalArgumentException("Field is required");
            // }
            
            // Validate KMIP spec compatibility
            KmipSpec spec = KmipContext.getSpec();
            // if (!isSupportedFor(spec)) {
            //     throw new UnsupportedOperationException(
            //         String.format("%s is not supported for KMIP spec %s", 
            //             getClass().getSimpleName(), spec)
            //     );
            // }
        }
    }
}
EOF
}

generate_ttlv_serializer() {
    local class_name=$1
    local package_path=$2
    local class_name_lower=$(get_camel_case "${class_name}")

    mkdir -p "src/main/java/org/purpleBean/kmip/codec/ttlv/serializer/kmip/${package_path}/"

    cat > "src/main/java/org/purpleBean/kmip/codec/ttlv/serializer/kmip/${package_path}/${class_name}TtlvSerializer.java" <<EOF
package org.purpleBean.kmip.codec.ttlv.serializer.kmip.${package_path//\//.};

import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipDataType;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.codec.ttlv.TtlvObject;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.codec.ttlv.serializer.kmip.KmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.${package_path//\//.}.${class_name};

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public class ${class_name}TtlvSerializer extends KmipDataTypeTtlvSerializer<${class_name}> {
    @Override
    public ByteBuffer serialize(${class_name} value, TtlvMapper mapper) throws IOException {
        return serializeToTtlvObject(value, mapper).toByteBuffer();
    }

    private TtlvObject serializeToTtlvObject(${class_name} value, TtlvMapper mapper) throws IOException {
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
}

generate_ttlv_deserializer() {
    local class_name=$1
    local package_path=$2
    local class_name_lower=$(get_camel_case "${class_name}")
    local class_name_snake=$(echo "$class_name" | sed -r 's/([a-z0-9])([A-Z])/\1_\2/g' | tr '[:lower:]' '[:upper:]')
    
    mkdir -p "src/main/java/org/purpleBean/kmip/codec/ttlv/deserializer/kmip/${package_path}/"
    
    cat > "src/main/java/org/purpleBean/kmip/codec/ttlv/deserializer/kmip/${package_path}/${class_name}TtlvDeserializer.java" <<EOF
package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.${package_path//\//.};

import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.ttlv.TtlvObject;
import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.KmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.common.*;
import org.purpleBean.kmip.common.enumeration.*;
import org.purpleBean.kmip.common.structure.*;
import org.purpleBean.kmip.${package_path//\//.}.${class_name};

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

public class ${class_name}TtlvDeserializer extends KmipDataTypeTtlvDeserializer<${class_name}> {
    private final EncodingType type = EncodingType.STRUCTURE;
    private final KmipTag kmipTag = new KmipTag(KmipTag.Standard.${class_name_snake});

    @Override
    public ${class_name} deserialize(ByteBuffer ttlvBuffer, TtlvMapper mapper) throws IOException {
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
        ${class_name}.${class_name}Builder builder = ${class_name}.builder();

        for (TtlvObject ttlvObject : nestedObjects) {
            KmipTag.Value nodeTag = KmipTag.fromBytes(spec, ttlvObject.getTag());
            setValue(builder, nodeTag, ttlvObject, mapper);
        }

        ${class_name} ${class_name_lower} = builder.build();

        if (!${class_name_lower}.isSupportedFor(spec)) {
            throw new NoSuchElementException(
                    String.format("%s is not supported for KMIP spec %s",
                            ${class_name_lower}.getClass().getSimpleName(), spec)
            );
        }
        return ${class_name_lower};
    }

    private void setValue(${class_name}.${class_name}Builder builder,
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
}

generate_json_serializer() {
    local class_name=$1
    local package_path=$2
    local class_name_lower=$(get_camel_case "${class_name}")
    
    echo "- Generating JSON serializer..."
    mkdir -p "src/main/java/org/purpleBean/kmip/codec/json/serializer/kmip/${package_path}/"
    
    cat > "src/main/java/org/purpleBean/kmip/codec/json/serializer/kmip/${package_path}/${class_name}JsonSerializer.java" <<EOF
package org.purpleBean.kmip.codec.json.serializer.kmip.${package_path//\//.};

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.codec.json.serializer.kmip.KmipDataTypeJsonSerializer;
import org.purpleBean.kmip.${package_path//\//.}.${class_name};

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

public class ${class_name}JsonSerializer extends KmipDataTypeJsonSerializer<${class_name}> {

    @Override
    public void serialize(${class_name} ${class_name_lower}, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        // Validation: Null check
        if (${class_name_lower} == null) {
            return;
        }

        // Validation: KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();
        if (!${class_name_lower}.isSupportedFor(spec)) {
            throw new UnsupportedEncodingException(
                String.format("%s is not supported for KMIP spec %s",
                    ${class_name_lower}.getClass().getSimpleName(), spec)
            );
        }

        List<KmipDataType> fields = ${class_name_lower}.getValues();
        // Validation: Field compatibility with KMIP spec
        for (KmipDataType field : fields) {
            if (field != null && !field.isSupportedFor(spec)) {
                throw new UnsupportedEncodingException(
                    String.format("%s in %s is not supported for KMIP spec %s",
                        field.getKmipTag().getDescription(),
                        ${class_name_lower}.getClass().getSimpleName(),
                        spec)
                );
            }
        }

        jsonGenerator.writeStartObject();
        jsonGenerator.writeObject(${class_name_lower}.getKmipTag());
        jsonGenerator.writeStringField("type", ${class_name_lower}.getEncodingType().getDescription());
        jsonGenerator.writeFieldName("value");
        jsonGenerator.writeStartArray();
        
        for (KmipDataType field : fields) {
            if (field != null) {
                jsonGenerator.writeObject(field);
            }
        }
        
        jsonGenerator.writeEndArray();
        jsonGenerator.writeEndObject();
    }
}
EOF
}

generate_json_deserializer() {
    local class_name=$1
    local package_path=$2
    local class_name_lower=$(get_camel_case "${class_name}")
    local class_name_snake=$(echo "$class_name" | sed -r 's/([a-z0-9])([A-Z])/\1_\2/g' | tr '[:lower:]' '[:upper:]')
    
    echo "- Generating JSON deserializer..."
    mkdir -p "src/main/java/org/purpleBean/kmip/codec/json/deserializer/kmip/${package_path}/"
    
    cat > "src/main/java/org/purpleBean/kmip/codec/json/deserializer/kmip/${package_path}/${class_name}JsonDeserializer.java" <<EOF
package org.purpleBean.kmip.codec.json.deserializer.kmip.${package_path//\//.};

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.${package_path//\//.}.${class_name};

import java.io.IOException;
import java.util.NoSuchElementException;

public class ${class_name}JsonDeserializer extends KmipDataTypeJsonDeserializer<${class_name}> {
    private final KmipTag kmipTag = new KmipTag(KmipTag.Standard.${class_name_snake});
    private final EncodingType encodingType = EncodingType.STRUCTURE;

    @Override
    public ${class_name} deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();
        if (node == null) {
            ctxt.reportInputMismatch(${class_name}.class, 
                "JSON node cannot be null for ${class_name} deserialization");
            return null;
        }

        // Validation: Extract and validate KMIP tag
        KmipTag tag;
        try {
            tag = p.getCodec().treeToValue(node, KmipTag.class);
            if (tag == null) {
                ctxt.reportInputMismatch(${class_name}.class, 
                    "Invalid KMIP tag for ${class_name}");
                return null;
            }
        } catch (Exception e) {
            ctxt.reportInputMismatch(${class_name}.class, 
                String.format("Failed to parse KMIP tag for ${class_name}: %s", e.getMessage()));
            return null;
        }

        if (!node.isObject() || tag.getValue().getValue() != kmipTag.getValue().getValue()) {
            ctxt.reportInputMismatch(${class_name}.class,
                String.format("Expected object with %s tag for ${class_name}, got tag: %s", 
                    kmipTag.getValue().getValue(), tag.getValue().getValue()));
            return null;
        }

        // Validation: Extract and validate type field
        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() || 
            !encodingType.getDescription().equals(typeNode.asText())) {
            ctxt.reportInputMismatch(${class_name}.class, 
                String.format("Invalid or missing type field for ${class_name}, expected: %s", 
                    encodingType.getDescription()));
            return null;
        }

        // Extract values array
        JsonNode valuesNode = node.get("value");
        if (valuesNode == null || !valuesNode.isArray()) {
            ctxt.reportInputMismatch(${class_name}.class, 
                "Missing or invalid 'value' array for ${class_name}");
            return null;
        }

        ${class_name}.${class_name}Builder builder = ${class_name}.builder();

        // Process each value in the array
        for (JsonNode valueNode : valuesNode) {
            if (valueNode == null || !valueNode.has("tag")) {
                continue;
            }

            try {
                KmipTag.Value nodeTag = p.getCodec().treeToValue(valueNode, KmipTag.class).getValue();
                setValue(builder, nodeTag, valueNode, p, ctxt);
            } catch (Exception e) {
                ctxt.reportInputMismatch(${class_name}.class, 
                    String.format("Failed to process field in ${class_name}: %s", e.getMessage()));
                return null;
            }
        }

        ${class_name} ${class_name_lower} = builder.build();

        // Validate the deserialized object
        if (!${class_name_lower}.isSupportedFor(KmipContext.getSpec())) {
            throw new NoSuchElementException(
                String.format("${class_name} is not supported for KMIP spec %s", 
                    KmipContext.getSpec())
            );
        }

        return ${class_name_lower};
    }

    /**
     * Sets the appropriate field in the builder based on the tag and value.
     */
    protected void setValue(${class_name}.${class_name}Builder builder,
                          KmipTag.Value nodeTag,
                          JsonNode node,
                          JsonParser p,
                          DeserializationContext ctxt) throws IOException {
        // TODO: Implement field deserialization based on nodeTag
        // Example:
        // switch (nodeTag) {
        //     case KmipTag.Standard.ACTIVATION_DATE ->
        //         builder.activationDate(p.getCodec().treeToValue(node, ActivationDateAttribute.class));
        //     case KmipTag.Standard.STATE -> 
        //         builder.state(p.getCodec().treeToValue(node, State.class));
        //     default -> throw new IllegalArgumentException("Unsupported field: " + nodeTag);
        // }
        throw new UnsupportedOperationException("Field deserialization not implemented for tag: " + nodeTag);
    }
}
EOF
}

generate_xml_serializer() {
    local class_name=$1
    local package_path=$2
    local class_name_lower=$(get_camel_case "${class_name}")
    
    echo "- Generating XML serializer..."
    mkdir -p "src/main/java/org/purpleBean/kmip/codec/xml/serializer/kmip/${package_path}/"
    
    cat > "src/main/java/org/purpleBean/kmip/codec/xml/serializer/kmip/${package_path}/${class_name}XmlSerializer.java" <<EOF
package org.purpleBean.kmip.codec.xml.serializer.kmip.${package_path//\//.};

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.codec.xml.serializer.kmip.KmipDataTypeXmlSerializer;
import org.purpleBean.kmip.${package_path//\//.}.${class_name};

import javax.xml.namespace.QName;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

public class ${class_name}XmlSerializer extends KmipDataTypeXmlSerializer<${class_name}> {

    @Override
    public void serialize(${class_name} ${class_name_lower}, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        // Validation: KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();
        if (!${class_name_lower}.isSupportedFor(spec)) {
            throw new UnsupportedEncodingException(
                String.format("%s not supported for KMIP spec %s",
                    ${class_name_lower}.getClass().getSimpleName(), spec)
            );
        }

        if (!(gen instanceof ToXmlGenerator xmlGen)) {
            throw new IllegalStateException("Expected ToXmlGenerator");
        }

        // Start element with name from kmipTag
        String elementName = ${class_name_lower}.getKmipTag().getDescription();
        xmlGen.setNextName(QName.valueOf(elementName));
        xmlGen.writeStartObject(${class_name_lower});

        // Serialize all fields
        List<KmipDataType> values = ${class_name_lower}.getValues();
        for (KmipDataType kmipDataType : values) {
            if (kmipDataType != null && kmipDataType.getKmipTag() != null) {
                serializers.defaultSerializeField(
                    kmipDataType.getKmipTag().getDescription(),
                    kmipDataType,
                    gen
                );
            }
        }

        gen.writeEndObject();
    }
}
EOF
}

generate_xml_deserializer() {
    local class_name=$1
    local package_path=$2
    local class_name_lower=$(get_camel_case "${class_name}")
    local class_name_snake=$(echo "$class_name" | sed -r 's/([a-z0-9])([A-Z])/\1_\2/g' | tr '[:lower:]' '[:upper:]')
    
    echo "- Generating XML deserializer..."
    mkdir -p "src/main/java/org/purpleBean/kmip/codec/xml/deserializer/kmip/${package_path}/"
    
    cat > "src/main/java/org/purpleBean/kmip/codec/xml/deserializer/kmip/${package_path}/${class_name}XmlDeserializer.java" <<EOF
package org.purpleBean.kmip.codec.xml.deserializer.kmip.${package_path//\//.};

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.dataformat.xml.deser.FromXmlParser;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.codec.xml.deserializer.kmip.KmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.${package_path//\//.}.${class_name};

import java.io.IOException;
import java.util.Map;
import java.util.NoSuchElementException;

public class ${class_name}XmlDeserializer extends KmipDataTypeXmlDeserializer<${class_name}> {
    private final KmipTag kmipTag = new KmipTag(KmipTag.Standard.${class_name_snake});
    private final EncodingType encodingType = EncodingType.STRUCTURE;

    @Override
    public ${class_name} deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(${class_name}.class, 
                "Expected XML object for ${class_name}");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
                && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(${class_name}.class, 
                String.format("Invalid Tag for ${class_name}, expected: %s", kmipTag.getDescription()));
            return null;
        }

        KmipSpec spec = KmipContext.getSpec();
        ${class_name}.${class_name}Builder builder = ${class_name}.builder();

        // Process all fields in the XML
        var fields = node.fields();
        while (fields.hasNext()) {
            Map.Entry<String, JsonNode> entry = fields.next();
            KmipTag.Value nodeTag = KmipTag.fromName(spec, entry.getKey());
            setValue(builder, nodeTag, entry.getValue(), p, ctxt);
        }

        ${class_name} ${class_name_lower} = builder.build();

        // Validate the deserialized object
        if (!${class_name_lower}.isSupportedFor(spec)) {
            throw new NoSuchElementException(
                String.format("${class_name} is not supported for KMIP spec %s", spec)
            );
        }

        return ${class_name_lower};
    }

    /**
     * Sets the appropriate field in the builder based on the tag and value.
     */
    protected void setValue(${class_name}.${class_name}Builder builder,
                          KmipTag.Value nodeTag,
                          JsonNode node,
                          JsonParser p,
                          DeserializationContext ctxt) throws IOException {
        // TODO: Implement field deserialization based on nodeTag
        // Example:
        // switch (nodeTag) {
        //     case KmipTag.Standard.ACTIVATION_DATE ->
        //         builder.activationDate(p.getCodec().treeToValue(node, ActivationDateAttribute.class));
        //     case KmipTag.Standard.STATE -> 
        //         builder.state(p.getCodec().treeToValue(node, State.class));
        //     default -> throw new IllegalArgumentException("Unsupported field: " + nodeTag);
        // }
        throw new UnsupportedOperationException("Field deserialization not implemented for tag: " + nodeTag);
    }
}
EOF
}

generate_benchmark_subject() {
    local class_name=$1
    local package_path=$2
    local class_name_lower=$(get_camel_case "${class_name}")
    
    mkdir -p "src/test/java/org/purpleBean/kmip/benchmark/subjects/${package_path}/"
    
    cat > "src/test/java/org/purpleBean/kmip/benchmark/subjects/${package_path}/${class_name}BenchmarkSubject.java" <<EOF
package org.purpleBean.kmip.benchmark.subjects.${package_path//\//.};

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.common.*;
import org.purpleBean.kmip.common.enumeration.*;
import org.purpleBean.kmip.common.structure.*;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.codec.json.KmipJsonModule;
import org.purpleBean.kmip.codec.ttlv.KmipTtlvModule;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.codec.xml.KmipXmlModule;
import org.purpleBean.kmip.${package_path//\//.}.${class_name};

import java.nio.ByteBuffer;
import java.time.OffsetDateTime;

public class ${class_name}BenchmarkSubject implements KmipBenchmarkSubject {

    private ObjectMapper json;
    private XmlMapper xml;
    private TtlvMapper ttlv;

    private ${class_name} obj;

    private String jsonStr;
    private String xmlStr;
    private ByteBuffer ttlvBuf;

    @Override
    public String name() {
        return "${class_name}";
    }

    @Override
    public void setup() throws Exception {
        // Configure mappers
        json = new ObjectMapper();
        json.findAndRegisterModules();
        json.registerModule(new JavaTimeModule());
        json.registerModule(new KmipJsonModule());

        xml = new XmlMapper();
        xml.findAndRegisterModules();
        xml.registerModule(new JavaTimeModule());
        xml.registerModule(new KmipXmlModule());

        ttlv = new TtlvMapper();
        ttlv.registerModule(new KmipTtlvModule());

        // Create test object with sample data
        obj = ${class_name}.builder()
                // TODO: Initialize with sample data
                .activationDate(ActivationDateAttribute.builder().dateTime(OffsetDateTime.now()).build())
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
        return json.readValue(jsonStr, ${class_name}.class);
    }

    @Override
    public String xmlSerialize() throws Exception {
        return xml.writeValueAsString(obj);
    }

    @Override
    public Object xmlDeserialize() throws Exception {
        return xml.readValue(xmlStr, ${class_name}.class);
    }

    @Override
    public ByteBuffer ttlvSerialize() throws Exception {
        return ttlv.writeValueAsByteBuffer(obj);
    }

    @Override
    public Object ttlvDeserialize() throws Exception {
        return ttlv.readValue(ttlvBuf.duplicate(), ${class_name}.class);
    }
}
EOF
}

generate_serialization_test() {
    local format="$1"
    local format_pascal=$(get_pascal_case "$format")
    local suite_name="${STRUCTURE_NAME}${format_pascal}Test"
    local base_suite="Abstract${format_pascal}SerializationSuite"

    echo "- Generating ${format_pascal} test..."
    mkdir -p "${TEST_JAVA}/codec/${format}/${SUB_PATH}"

    cat > "${TEST_JAVA}/codec/${format}/${SUB_PATH}/${suite_name}.java" << EOF
package org.purpleBean.kmip.codec.${format}.${SUB_PATH//\//.};

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.${SUB_PATH//\//.}.${STRUCTURE_NAME};
import org.purpleBean.kmip.common.*;
import org.purpleBean.kmip.common.enumeration.*;
import org.purpleBean.kmip.common.structure.*;
import org.purpleBean.kmip.test.suite.${base_suite};

import java.time.OffsetDateTime;

@DisplayName("${STRUCTURE_NAME} ${format_pascal} Serialization Tests")
class ${suite_name} extends ${base_suite}<${STRUCTURE_NAME}> {

    @Override
    protected Class<${STRUCTURE_NAME}> type() {
        return ${STRUCTURE_NAME}.class;
    }

    @Override
    protected ${STRUCTURE_NAME} createDefault() {
        return ${STRUCTURE_NAME}.builder()
            // TODO: Set default values for serialization test
            .activationDate(ActivationDateAttribute.builder().dateTime(OffsetDateTime.now()).build())
            .build();
    }

    @Override
    protected ${STRUCTURE_NAME} createVariant() {
        return ${STRUCTURE_NAME}.builder()
            // TODO: Set different values for variant test
            .activationDate(ActivationDateAttribute.builder().dateTime(OffsetDateTime.now()).build())
            .build();
    }
}
EOF
}

# Create directories
create_directories "${MAIN_JAVA}" "${TEST_JAVA}" "${SUB_PATH}"

# Process each structure name
for structure_name in "${STRUCTURE_NAMES[@]}"; do
    if [[ "${structure_name}" != *Attribute ]]; then
        generate_attribute_structure "${structure_name}Attribute"
    else
        generate_attribute_structure "${structure_name}"
    fi
done

echo -e "\n✓ Successfully generated ${#STRUCTURE_NAMES[@]} attribute structure(s)"

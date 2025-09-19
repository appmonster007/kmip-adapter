#!/bin/bash

# Check if structure names are provided
if [ $# -eq 0 ]; then
    echo "Usage: $0 <StructureName1> [<StructureName2> ...]"
    echo "Example: $0 ProtocolVersion"
    echo "Example: $0 MyNewStructure"
    exit 1
fi

# Store all structure names in an array
STRUCTURE_NAMES=("$@")

# Function to convert structure name to variable name
get_structure_var_name() {
    local name="$1"
    echo "${name:0:1}" | tr '[:upper:]' '[:lower:]'"${name:1}"
}

# Function to convert structure name to snake case
get_structure_snake_case() {
    local name="$1"
    echo "$name" | sed -r 's/([A-Z])/_\1/g' | sed 's/^_//' | tr '[:lower:]' '[:upper:]'
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

# Function to create all required directories
create_directories() {
    local main_java="$1"
    local test_java="$2"
    local sub_path="$3"
    
    echo "Creating directory structure..."
    
    # Main source directories
    mkdir -p "${main_java}/${sub_path}"
    mkdir -p "${main_java}/codec/json/serializer/kmip/${sub_path}"
    mkdir -p "${main_java}/codec/json/deserializer/kmip/${sub_path}"
    mkdir -p "${main_java}/codec/xml/serializer/kmip/${sub_path}"
    mkdir -p "${main_java}/codec/xml/deserializer/kmip/${sub_path}"
    mkdir -p "${main_java}/codec/ttlv/serializer/kmip/${sub_path}"
    mkdir -p "${main_java}/codec/ttlv/deserializer/kmip/${sub_path}"
    
    # Test directories
    mkdir -p "${test_java}/codec/json/${sub_path}"
    mkdir -p "${test_java}/codec/xml/${sub_path}"
    mkdir -p "${test_java}/codec/ttlv/${sub_path}"
    mkdir -p "${test_java}/benchmark/subjects/${sub_path}"
    
    # META-INF/services directories for service loader
    mkdir -p "src/main/resources/META-INF/services"
    mkdir -p "src/test/resources/META-INF/services"
    
    echo "Directory structure created successfully"
}

# Base directories
BASE_DIR="$(pwd)"
MAIN_JAVA="src/main/java/org/purpleBean/kmip"
TEST_JAVA="src/test/java/org/purpleBean/kmip"
SUB_PATH="common/structure"

# Function to generate files for a single structure
generate_structure() {
    local STRUCTURE_NAME="$1"
    local STRUCTURE_VAR_NAME=$(get_structure_var_name "$STRUCTURE_NAME")
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
import org.purpleBean.kmip.structure.KmipStructure;

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

            return new ${STRUCTURE_NAME}(activationDate, state);;
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
EOF

    echo "✓ Main structure class generated"
    
    # 2. Create JSON Serializer
    echo "- Generating JSON serializer..."
    cat > "${MAIN_JAVA}/codec/json/serializer/kmip/${SUB_PATH}/${STRUCTURE_NAME}JsonSerializer.java" << 'EOF'
package org.purpleBean.kmip.codec.json.serializer.kmip.${SUB_PATH//\//.};

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.common.*;
import org.purpleBean.kmip.common.enumeration.*;
import org.purpleBean.kmip.common.structure.*;
import org.purpleBean.kmip.codec.json.serializer.kmip.KmipDataTypeJsonSerializer;
import org.purpleBean.kmip.${SUB_PATH}.${STRUCTURE_NAME};

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
    cat > "${MAIN_JAVA}/codec/json/deserializer/kmip/${SUB_PATH}/${STRUCTURE_NAME}JsonDeserializer.java" << 'EOF'
package org.purpleBean.kmip.codec.json.deserializer.kmip.${SUB_PATH//\//.};

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.common.*;
import org.purpleBean.kmip.common.enumeration.*;
import org.purpleBean.kmip.common.structure.*;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.${SUB_PATH}.${STRUCTURE_NAME};

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
            
            KmipTag.Value nodeTag = p.getCodec().treeToValue(valueNode.get("tag"), KmipTag.class).getValue();
            setValue(builder, nodeTag, valueNode, p, ctxt);
        }

        ${STRUCTURE_NAME} result = builder.build();

        // Validate KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();
        if (!result.isSupportedFor(spec)) {
            throw new NoSuchElementException(
                String.format("${STRUCTURE_NAME} is not supported for KMIP spec %s", spec)
            );
        }

        return result;
    }

    /**
     * Sets the appropriate field in the builder based on the tag and value.
     *
     * @param builder the builder to set the field on
     * @param tag the tag identifying the field to set
     * @param valueNode the JSON node containing the field value
     * @param p the JsonParser
     * @param ctxt the DeserializationContext
     * @throws IOException if there is an error deserializing the value
     */
    protected void setValue(${STRUCTURE_NAME}.${STRUCTURE_NAME}Builder builder, 
                          KmipTag.Value tag,
                          JsonNode valueNode,
                          JsonParser p,
                          DeserializationContext ctxt) throws IOException {
        // TODO: Implement field deserialization based on tag, preferably using switch case expression
        // Example:
        if (tag == KmipTag.Standard.ACTIVATION_DATE) {
            builder.activationDate(p.getCodec().treeToValue(valueNode, ActivationDate.class));
            return;
        }
        throw new UnsupportedOperationException(
            String.format("Field deserialization for tag %s not implemented", tag)
        );
    }
}
EOF

    # 4. Create XML Serializer
    cat > "${MAIN_JAVA}/codec/xml/serializer/kmip/${SUB_PATH}/${STRUCTURE_NAME}XmlSerializer.java" << 'EOF'
package org.purpleBean.kmip.codec.xml.serializer.kmip.${SUB_PATH//\//.};

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.common.*;
import org.purpleBean.kmip.common.enumeration.*;
import org.purpleBean.kmip.common.structure.*;
import org.purpleBean.kmip.codec.xml.serializer.kmip.KmipDataTypeXmlSerializer;
import org.purpleBean.kmip.${SUB_PATH}.${STRUCTURE_NAME};

import javax.xml.namespace.QName;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

public class ${STRUCTURE_NAME}XmlSerializer extends KmipDataTypeXmlSerializer<${STRUCTURE_NAME}> {

    @Override
    public void serialize(${STRUCTURE_NAME} value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        // Validation: KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();
        if (!value.isSupportedFor(spec)) {
            throw new UnsupportedEncodingException(
                String.format("%s not supported for KMIP spec %s", 
                    value.getClass().getSimpleName(), spec)
            );
        }

        if (!(gen instanceof ToXmlGenerator xmlGen)) {
            throw new IllegalStateException("Expected ToXmlGenerator");
        }

        // Start element with name from kmipTag
        String elementName = value.getKmipTag().getDescription();
        xmlGen.setNextName(QName.valueOf(elementName));
        xmlGen.writeStartObject(value);

        // Serialize all fields
        List<KmipDataType> values = value.getValues();
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
    cat > "${MAIN_JAVA}/codec/xml/deserializer/kmip/${SUB_PATH}/${STRUCTURE_NAME}XmlDeserializer.java" << 'EOF'
package org.purpleBean.kmip.codec.xml.deserializer.kmip.${SUB_PATH//\//.};

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.xml.deserializer.kmip.KmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.${SUB_PATH}.${STRUCTURE_NAME};

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
    protected void setValue(${STRUCTURE_NAME}.${STRUCTURE_NAME}Builder builder, 
                          KmipTag.Value nodeTag,
                          JsonNode node,
                          JsonParser p,
                          DeserializationContext ctxt) throws IOException {
        // TODO: Implement field deserialization based on nodeTag
        // Example:
        if (nodeTag == KmipTag.Standard.ACTIVATION_DATE) {
            builder.activationDate(p.getCodec().treeToValue(node, ActivationDate.class));
            return;
        }
        throw new UnsupportedOperationException(
            String.format("Field deserialization for tag %s not implemented", nodeTag)
        );
    }
}
EOF

    # Add service entries for the structure
    echo "- Registering service entries..."
    
    # JSON Serializer
    add_service_entry "src/main/resources/META-INF/services/com.fasterxml.jackson.databind.JsonSerializer" \
        "org.purpleBean.kmip.codec.json.serializer.kmip.${SUB_PATH//\//.}.${STRUCTURE_NAME}JsonSerializer"
    
    # JSON Deserializer
    add_service_entry "src/main/resources/META-INF/services/com.fasterxml.jackson.databind.JsonDeserializer" \
        "org.purpleBean.kmip.codec.json.deserializer.kmip.${SUB_PATH//\//.}.${STRUCTURE_NAME}JsonDeserializer"
    
    # XML Serializer
    add_service_entry "src/main/resources/META-INF/services/com.fasterxml.jackson.databind.JsonSerializer" \
        "org.purpleBean.kmip.codec.xml.serializer.kmip.${SUB_PATH//\//.}.${STRUCTURE_NAME}XmlSerializer"
    
    # XML Deserializer
    add_service_entry "src/main/resources/META-INF/services/com.fasterxml.jackson.databind.JsonDeserializer" \
        "org.purpleBean.kmip.codec.xml.deserializer.kmip.${SUB_PATH//\//.}.${STRUCTURE_NAME}XmlDeserializer"
    
    # TTLV Serializer
    add_service_entry "src/main/resources/META-INF/services/org.purpleBean.kmip.codec.ttlv.TtlvSerializer" \
        "org.purpleBean.kmip.codec.ttlv.serializer.kmip.${SUB_PATH//\//.}.${STRUCTURE_NAME}TtlvSerializer"
    
    # TTLV Deserializer
    add_service_entry "src/main/resources/META-INF/services/org.purpleBean.kmip.codec.ttlv.TtlvDeserializer" \
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

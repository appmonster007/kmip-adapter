#!/bin/bash

# Check if enum names are provided
if [ $# -eq 0 ]; then
    echo "Usage: $0 <EnumName1> [<EnumName2> ...]"
    echo "Example: $0 State Operation ObjectType"
    echo "Example: $0 MyNewEnum"
    exit 1
fi

# Store all enum names in an array
ENUM_NAMES=("$@")
# Function to convert enum name to variable name
get_enum_var_name() {
    local name="$1"
    echo "${name:0:1}" | tr '[:upper:]' '[:lower:]'"${name:1}"
}

# Function to create all required directories
create_directories() {
    local main_java="$1"
    local test_java="$2"
    local sub_path="$3"
    
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

# Base directories
BASE_DIR="$(pwd)"
MAIN_JAVA="src/main/java/org/purpleBean/kmip"
TEST_JAVA="src/test/java/org/purpleBean/kmip"
SUB_PATH="common/enumeration"

# Function to generate files for a single enum
generate_enum() {
    local ENUM_NAME="$1"
    local ENUM_NAME_SNAKE=$(echo "${ENUM_NAME}" | sed -r 's/([A-Z])/_\1/g' | sed 's/^_//' | tr 'a-z' 'A-Z')
    local ENUM_NAME_LOWERCASE=$(echo "${ENUM_NAME}" | tr '[:upper:]' '[:lower:]')
    local ENUM_VAR_NAME=$(get_enum_var_name "$ENUM_NAME")
    
    echo -e "\nGenerating files for ${ENUM_NAME}..."
    
    # 1. Create the main enum class
    cat > "${MAIN_JAVA}/${SUB_PATH}/${ENUM_NAME}.java" << EOF
package org.purpleBean.kmip.${SUB_PATH//\//.};

import lombok.*;
import org.purpleBean.kmip.*;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * KMIP ${ENUM_NAME} enumeration.
 */
@Data
@Builder
public class ${ENUM_NAME} implements KmipEnumeration {
    private static final Map<Integer, Value> VALUE_REGISTRY = new ConcurrentHashMap<>();
    private static final Map<String, Value> DESCRIPTION_REGISTRY = new ConcurrentHashMap<>();
    private static final Map<String, Value> EXTENSION_DESCRIPTION_REGISTRY = new ConcurrentHashMap<>();

    static {
        for (Standard s : Standard.values()) {
            VALUE_REGISTRY.put(s.value, s);
            DESCRIPTION_REGISTRY.put(s.description, s);
        }
    }

    private final KmipTag kmipTag = new KmipTag(KmipTag.Standard.${ENUM_NAME_SNAKE});
    private final EncodingType encodingType = EncodingType.ENUMERATION;

    @NonNull
    private final Value value;

    public ${ENUM_NAME}(@NonNull Value value) {
        // KMIP spec compatibility validation
        KmipSpec spec = KmipContext.getSpec();
        if (!value.isSupportedFor(spec)) {
            throw new IllegalArgumentException(
                    String.format("Value '%s' for ${ENUM_NAME} is not supported for KMIP spec %s", value.getDescription(), spec)
            );
        }
        this.value = value;
    }

    private static void checkValidExtensionValue(int value) {
        int extensionStart = 0x80000000;
        if (value < extensionStart || value > 0) {
            throw new IllegalArgumentException(
                    String.format("Extension value %d must be in range 8XXXXXXX (hex)", value)
            );
        }
    }

    /**
     * Register an extension value.
     */
    public static Value register(int value, @NonNull String description, @NonNull Set<KmipSpec> supportedVersions) {
        checkValidExtensionValue(value);
        if (description.trim().isEmpty()) {
            throw new IllegalArgumentException("Description cannot be empty");
        }
        if (supportedVersions.isEmpty()) {
            throw new IllegalArgumentException("At least one supported version must be specified");
        }
        Value existingEnumByValue = VALUE_REGISTRY.get(value);
        Value existingEnumByDescription = EXTENSION_DESCRIPTION_REGISTRY.get(description);
        if (existingEnumByValue != null || existingEnumByDescription != null) {
            return existingEnumByValue != null ? existingEnumByValue : existingEnumByDescription;
        }
        Extension custom = new Extension(value, description, supportedVersions);
        VALUE_REGISTRY.putIfAbsent(custom.getValue(), custom);
        DESCRIPTION_REGISTRY.putIfAbsent(custom.getDescription(), custom);
        EXTENSION_DESCRIPTION_REGISTRY.putIfAbsent(custom.getDescription(), custom);
        return custom;
    }

    /**
     * Look up by name.
     */
    public static Value fromName(KmipSpec spec, String name) {
        Value v = DESCRIPTION_REGISTRY.get(name);
        return Optional.ofNullable(v)
                .filter(x -> x.isSupportedFor(spec))
                .orElseThrow(() -> new NoSuchElementException(
                        String.format("No ${ENUM_NAME} value found for '%s' in KMIP spec %s", name, spec)
                ));
    }

    /**
     * Look up by value.
     */
    public static Value fromValue(KmipSpec spec, int value) {
        // Check standard values first
        Value v = VALUE_REGISTRY.get(value);
        return Optional.ofNullable(v)
                .filter(x -> x.isSupportedFor(spec))
                .orElseThrow(() -> new NoSuchElementException(
                        String.format("No ${ENUM_NAME} value found for %d in KMIP spec %s", value, spec)
                ));
    }

    /**
     * Get registered values.
     */
    public static Collection<Value> registeredValues() {
        return List.copyOf(EXTENSION_DESCRIPTION_REGISTRY.values());
    }

    public String getDescription() {
        return value.getDescription();
    }

    public boolean isCustom() {
        return value.isCustom();
    }

    @Override
    public boolean isSupportedFor(@NonNull KmipSpec spec) {
        return value.isSupportedFor(spec);
    }

    @Getter
    @AllArgsConstructor
    @ToString
    public enum Standard implements Value {
        PLACEHOLDER_1(0x00000001, "Placeholder1", KmipSpec.UnknownVersion ),
        PLACEHOLDER_2(0x00000002, "Placeholder2", KmipSpec.UnknownVersion );

        private final int value;
        private final String description;
        private final Set<KmipSpec> supportedVersions;

        private final boolean custom = false;

        Standard(int value, String description, KmipSpec... supportedVersions) {
            this.value = value;
            this.description = description;
            this.supportedVersions = Set.of(supportedVersions);
        }

        @Override
        public boolean isSupportedFor(KmipSpec spec) {
            return supportedVersions.contains(spec);
        }
    }

    // ----- Value hierarchy -----
    public interface Value {
        int getValue();

        String getDescription();

        boolean isSupportedFor(KmipSpec spec);

        boolean isCustom();
    }

    @Getter
    @AllArgsConstructor
    @ToString
    public static class Extension implements Value {
        private final int value;
        private final String description;
        private final Set<KmipSpec> supportedVersions;

        private final boolean custom = true;

        public Extension(int value, String description, KmipSpec... supportedVersions) {
            this.value = value;
            this.description = description;
            this.supportedVersions = Set.of(supportedVersions);
        }

        @Override
        public boolean isSupportedFor(KmipSpec spec) {
            return supportedVersions.contains(spec);
        }
    }
}
EOF

# 2. Create JSON Serializer
cat > "${MAIN_JAVA}/codec/json/serializer/kmip/${SUB_PATH}/${ENUM_NAME}JsonSerializer.java" << EOF
package org.purpleBean.kmip.codec.json.serializer.kmip.${SUB_PATH//\//.};

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.codec.json.serializer.kmip.KmipDataTypeJsonSerializer;
import org.purpleBean.kmip.${SUB_PATH//\//.}.${ENUM_NAME};

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * JSON serializer for ${ENUM_NAME}.
 */
public class ${ENUM_NAME}JsonSerializer extends KmipDataTypeJsonSerializer<${ENUM_NAME}> {

    @Override
    public void serialize(${ENUM_NAME} value, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {

        if (value == null) {
            return;
        }

        // Validation: KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();
        if (!value.isSupportedFor(spec)) {
            throw new UnsupportedEncodingException(
                    String.format("${ENUM_NAME} '%s' is not supported for KMIP spec %s",
                            value.getDescription(), spec)
            );
        }

        if (value.getDescription() == null || value.getDescription().trim().isEmpty()) {
            throw new IllegalStateException("${ENUM_NAME} must have a valid description");
        }

        jsonGenerator.writeStartObject();
        jsonGenerator.writeObject(value.getKmipTag());
        jsonGenerator.writeStringField("type", value.getEncodingType().getDescription());
        jsonGenerator.writeStringField("value", value.getDescription());
        jsonGenerator.writeEndObject();
    }
}
EOF

# 3. Create JSON Deserializer
cat > "${MAIN_JAVA}/codec/json/deserializer/kmip/${SUB_PATH}/${ENUM_NAME}JsonDeserializer.java" << EOF
package org.purpleBean.kmip.codec.json.deserializer.kmip.${SUB_PATH//\//.};

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.${SUB_PATH//\//.}.${ENUM_NAME};

import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * JSON deserializer for ${ENUM_NAME}.
 */
public class ${ENUM_NAME}JsonDeserializer extends KmipDataTypeJsonDeserializer<${ENUM_NAME}> {
    private final KmipTag kmipTag = new KmipTag(KmipTag.Standard.${ENUM_NAME_SNAKE});
    private final EncodingType encodingType = EncodingType.ENUMERATION;

    @Override
    public ${ENUM_NAME} deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();
        if (node == null) {
            ctxt.reportInputMismatch(${ENUM_NAME}.class, String.format("JSON node cannot be null for ${ENUM_NAME} deserialization"));
            return null;
        }

        // Validation: Extract and validate KMIP tag
        KmipTag tag;
        try {
            tag = p.getCodec().treeToValue(node, KmipTag.class);
            if (tag == null) {
                ctxt.reportInputMismatch(${ENUM_NAME}.class, String.format("Invalid KMIP tag for ${ENUM_NAME}"));
                return null;
            }
        } catch (Exception e) {
            ctxt.reportInputMismatch(${ENUM_NAME}.class, String.format("Failed to parse KMIP tag for ${ENUM_NAME}: %s", e.getMessage()));
            return null;
        }

        if (!node.isObject() || tag.getValue().getValue() != kmipTag.getValue().getValue()) {
            ctxt.reportInputMismatch(${ENUM_NAME}.class,
                    String.format("Expected object with %s tag for ${ENUM_NAME}, got tag: %s", kmipTag.getValue().getValue(), tag.getValue().getValue()));
            return null;
        }

        // Validation: Extract and validate type field
        JsonNode typeNode = node.get("type");
        if (typeNode == null
                || !typeNode.isTextual()
                || EncodingType.fromName(typeNode.asText()).isEmpty()
                || EncodingType.fromName(typeNode.asText()).get() != encodingType
        ) {
            ctxt.reportInputMismatch(${ENUM_NAME}.class, String.format("Missing or non-text 'type' field for ${ENUM_NAME}"));
            return null;
        }

        // Validation: Extract and validate value field
        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(${ENUM_NAME}.class, String.format("Missing or non-text 'value' field for %s", kmipTag.getDescription()));
            return null;
        }

        String description = valueNode.asText();
        if (description == null || description.trim().isEmpty()) {
            ctxt.reportInputMismatch(${ENUM_NAME}.class, String.format("%s value cannot be empty", kmipTag.getDescription()));
            return null;
        }

        // Validation: KMIP spec compatibility and value lookup
        KmipSpec spec = KmipContext.getSpec();
        ${ENUM_NAME}.Value ${ENUM_NAME_LOWERCASE}Value;
        try {
            ${ENUM_NAME_LOWERCASE}Value = ${ENUM_NAME}.fromName(spec, description);
        } catch (NoSuchElementException e) {
            ctxt.reportInputMismatch(${ENUM_NAME}.class,
                    String.format("Unknown ${ENUM_NAME} value '%s' for KMIP spec %s", description, spec));
            return null;
        }

        ${ENUM_NAME} ${ENUM_NAME_LOWERCASE} = new ${ENUM_NAME}(${ENUM_NAME_LOWERCASE}Value);

        // Final validation: Ensure constructed ${ENUM_NAME} is supported
        if (!${ENUM_NAME_LOWERCASE}.isSupportedFor(spec)) {
            throw new NoSuchElementException(
                    String.format("${ENUM_NAME} '%s' is not supported for KMIP spec %s", description, spec)
            );
        }

        return ${ENUM_NAME_LOWERCASE};
    }
}
EOF

# 4. Create XML Serializer
cat > "${MAIN_JAVA}/codec/xml/serializer/kmip/${SUB_PATH}/${ENUM_NAME}XmlSerializer.java" << EOF
package org.purpleBean.kmip.codec.xml.serializer.kmip.${SUB_PATH//\//.};

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.codec.xml.serializer.kmip.KmipDataTypeXmlSerializer;
import org.purpleBean.kmip.${SUB_PATH//\//.}.${ENUM_NAME};

import javax.xml.namespace.QName;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * XML serializer for ${ENUM_NAME}.
 */
public class ${ENUM_NAME}XmlSerializer extends KmipDataTypeXmlSerializer<${ENUM_NAME}> {

    @Override
    public void serialize(${ENUM_NAME} value, JsonGenerator gen, SerializerProvider provider) throws IOException {
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
        xmlGen.writeStringField("value", value.getDescription());
        xmlGen.writeEndObject();
    }
}

EOF

# 5. Create XML Deserializer
cat > "${MAIN_JAVA}/codec/xml/deserializer/kmip/${SUB_PATH}/${ENUM_NAME}XmlDeserializer.java" << EOF
package org.purpleBean.kmip.codec.xml.deserializer.kmip.${SUB_PATH//\//.};

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
import org.purpleBean.kmip.${SUB_PATH//\//.}.${ENUM_NAME};

import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * XML deserializer for ${ENUM_NAME}.
 */
public class ${ENUM_NAME}XmlDeserializer extends KmipDataTypeXmlDeserializer<${ENUM_NAME}> {
    private final EncodingType encodingType = EncodingType.ENUMERATION;
    private final KmipTag kmipTag = new KmipTag(KmipTag.Standard.${ENUM_NAME_SNAKE});

    @Override
    public ${ENUM_NAME} deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(${ENUM_NAME}.class, "Expected XML element object for ${ENUM_NAME}");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
                && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(${ENUM_NAME}.class, "Invalid Tag for ${ENUM_NAME}");
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() ||
                !encodingType.getDescription().equals(typeNode.asText())) {
            ctxt.reportInputMismatch(${ENUM_NAME}.class, "Missing or invalid '@type' attribute for ${ENUM_NAME}");
            return null;
        }

        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(${ENUM_NAME}.class, "Missing or non-text '@value' attribute for ${ENUM_NAME}");
            return null;
        }

        String description = valueNode.asText();
        KmipSpec spec = KmipContext.getSpec();

        ${ENUM_NAME} ${ENUM_NAME_LOWERCASE} = new ${ENUM_NAME}(${ENUM_NAME}.fromName(spec, description));
        if (!${ENUM_NAME_LOWERCASE}.isSupportedFor(spec)) {
            throw new NoSuchElementException(
                String.format("${ENUM_NAME} '%s' not supported for spec %s", description, spec));
        }

        return ${ENUM_NAME_LOWERCASE};
    }
}
EOF

# 6. Create TTLV Serializer
cat > "${MAIN_JAVA}/codec/ttlv/serializer/kmip/${SUB_PATH}/${ENUM_NAME}TtlvSerializer.java" << EOF
package org.purpleBean.kmip.codec.ttlv.serializer.kmip.${SUB_PATH//\//.};

import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.codec.ttlv.TtlvObject;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.codec.ttlv.serializer.kmip.KmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.${SUB_PATH//\//.}.${ENUM_NAME};

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

/**
 * TTLV serializer for ${ENUM_NAME}.
 */
public class ${ENUM_NAME}TtlvSerializer extends KmipDataTypeTtlvSerializer<${ENUM_NAME}> {

    @Override
    public ByteBuffer serialize(${ENUM_NAME} value, TtlvMapper mapper) throws IOException {
        return serializeToTtlvObject(value, mapper).toByteBuffer();
    }

    public TtlvObject serializeToTtlvObject(${ENUM_NAME} value, TtlvMapper mapper) throws IOException {
        KmipSpec spec = KmipContext.getSpec();
        if (!value.isSupportedFor(spec)) {
            throw new UnsupportedEncodingException();
        }

        byte[] tag = value.getKmipTag().getTagBytes();
        byte type = value.getEncodingType().getTypeValue();
        byte[] payload = mapper.writeValueAsByteBuffer(value.getValue().getValue()).array();

        return TtlvObject.builder()
                .tag(tag)
                .type(type)
                .value(payload)
                .build();
    }
}
EOF

# 7. Create TTLV Deserializer
cat > "${MAIN_JAVA}/codec/ttlv/deserializer/kmip/${SUB_PATH}/${ENUM_NAME}TtlvDeserializer.java" << EOF
package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.${SUB_PATH//\//.};

import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.ttlv.TtlvConstants;
import org.purpleBean.kmip.codec.ttlv.TtlvObject;
import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.KmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.${SUB_PATH//\//.}.${ENUM_NAME};

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * TTLV deserializer for ${ENUM_NAME}.
 */
public class ${ENUM_NAME}TtlvDeserializer extends KmipDataTypeTtlvDeserializer<${ENUM_NAME}> {
    private final EncodingType encodingType = EncodingType.ENUMERATION;
    private final KmipTag kmipTag = new KmipTag(KmipTag.Standard.${ENUM_NAME_SNAKE});

    @Override
    public ${ENUM_NAME} deserialize(ByteBuffer ttlvBuffer, TtlvMapper mapper) throws IOException {
        TtlvObject obj = TtlvObject.fromBuffer(ttlvBuffer);
        if (Arrays.equals(obj.getTag(), kmipTag.getTagBytes())
                && obj.getType() != encodingType.getTypeValue()) {
            throw new IllegalArgumentException(String.format("Expected %s type for ${ENUM_NAME}", encodingType.getTypeValue()));
        }
        ByteBuffer bb = ByteBuffer.wrap(obj.getValue()).order(TtlvConstants.BYTE_ORDER);
        int value = bb.getInt();

        KmipSpec spec = KmipContext.getSpec();
        ${ENUM_NAME} ${ENUM_NAME_LOWERCASE} = new ${ENUM_NAME}(${ENUM_NAME}.fromValue(spec, value));

        if (!${ENUM_NAME_LOWERCASE}.isSupportedFor(spec)) {
            throw new NoSuchElementException();
        }
        return ${ENUM_NAME_LOWERCASE};
    }
}
EOF

# 8. Create Domain Test
cat > "${TEST_JAVA}/${SUB_PATH}/${ENUM_NAME}Test.java" << EOF
package org.purpleBean.kmip.${SUB_PATH//\//.};

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.test.suite.AbstractKmipEnumerationSuite;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("${ENUM_NAME} Domain Tests")
class ${ENUM_NAME}Test extends AbstractKmipEnumerationSuite<${ENUM_NAME}> {

    @Override
    protected Class<${ENUM_NAME}> type() {
        return ${ENUM_NAME}.class;
    }

    @Override
    protected ${ENUM_NAME} createDefault() {
        return new ${ENUM_NAME}(${ENUM_NAME}.Standard.PLACEHOLDER_1);
    }

    @Override
    protected ${ENUM_NAME} createEqualToDefault() {
        return new ${ENUM_NAME}(${ENUM_NAME}.Standard.PLACEHOLDER_1);
    }

    @Override
    protected ${ENUM_NAME} createDifferentFromDefault() {
        return new ${ENUM_NAME}(${ENUM_NAME}.Standard.PLACEHOLDER_2);
    }

    @Override
    protected EncodingType expectedEncodingType() {
        return EncodingType.ENUMERATION;
    }

    @Override
    protected boolean supportsRegistryBehavior() {
        return true;
    }

    @Override
    protected void assertLookupBehaviour() {
        // Lookup by name/value
        ${ENUM_NAME}.Value byName = ${ENUM_NAME}.fromName(KmipSpec.UnknownVersion, "X-Enum-Custom");
        ${ENUM_NAME}.Value byVal = ${ENUM_NAME}.fromValue(KmipSpec.UnknownVersion, 0x80000010);
        assertThat(byName.getDescription()).isEqualTo("X-Enum-Custom");
        assertThat(byVal.getValue()).isEqualTo(0x80000010);

        // Lookup by name/value with unsupported version
        assertThatThrownBy(() -> ${ENUM_NAME}.fromName(KmipSpec.UnsupportedVersion, "X-Enum-Custom"));
    }

    @Override
    protected void assertEnumerationRegistryBehavior() {
        // Valid registration in ${ENUM_NAME} requires 8XXXXXXX (hex) range per implementation
        ${ENUM_NAME}.Value custom = ${ENUM_NAME}.register(0x80000010, "X-Enum-Custom", Set.of(KmipSpec.UnknownVersion ));
        assertThat(custom.isCustom()).isTrue();
        assertThat(custom.getDescription()).isEqualTo("X-Enum-Custom");
        assertThat(custom.isSupportedFor(KmipSpec.UnknownVersion)).isTrue();
        assertThat(custom.isSupportedFor(KmipSpec.UnsupportedVersion)).isFalse();

        // Negative cases: invalid range, empty description, empty versions
        assertThatThrownBy(() -> ${ENUM_NAME}.register(0x7FFFFFFF, "Bad-Range", Set.of(KmipSpec.UnknownVersion )))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> ${ENUM_NAME}.register(0x00000001, "Bad-Range", Set.of(KmipSpec.UnknownVersion )))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> ${ENUM_NAME}.register(0x80000011, "   ", Set.of(KmipSpec.UnknownVersion )))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> ${ENUM_NAME}.register(0x80000012, "X-Empty-Versions", Set.of()))
                .isInstanceOf(IllegalArgumentException.class);
    }
}

EOF

# 9. Create JSON Test
cat > "${TEST_JAVA}/codec/json/${SUB_PATH}/${ENUM_NAME}JsonTest.java" << EOF
package org.purpleBean.kmip.codec.json.${SUB_PATH//\//.};

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.${SUB_PATH//\//.}.${ENUM_NAME};
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationSuite;

@DisplayName("${ENUM_NAME} JSON Serialization")
class ${ENUM_NAME}JsonTest extends AbstractJsonSerializationSuite<${ENUM_NAME}> {
    @Override
    protected Class<${ENUM_NAME}> type() {
        return ${ENUM_NAME}.class;
    }

    @Override
    protected ${ENUM_NAME} createDefault() {
        return new ${ENUM_NAME}(${ENUM_NAME}.Standard.PLACEHOLDER_1);
    }

    @Override
    protected ${ENUM_NAME} createVariant() {
        return new ${ENUM_NAME}(${ENUM_NAME}.Standard.PLACEHOLDER_2);
    }
}
EOF

# 10. Create XML Test
cat > "${TEST_JAVA}/codec/xml/${SUB_PATH}/${ENUM_NAME}XmlTest.java" << EOF
package org.purpleBean.kmip.codec.xml.${SUB_PATH//\//.};

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.${SUB_PATH//\//.}.${ENUM_NAME};
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationSuite;

@DisplayName("${ENUM_NAME} XML Serialization")
class ${ENUM_NAME}XmlTest extends AbstractXmlSerializationSuite<${ENUM_NAME}> {
    @Override
    protected Class<${ENUM_NAME}> type() {
        return ${ENUM_NAME}.class;
    }

    @Override
    protected ${ENUM_NAME} createDefault() {
        return new ${ENUM_NAME}(${ENUM_NAME}.Standard.PLACEHOLDER_1);
    }

    @Override
    protected ${ENUM_NAME} createVariant() {
        return new ${ENUM_NAME}(${ENUM_NAME}.Standard.PLACEHOLDER_2);
    }
}
EOF

# 11. Create TTLV Test
cat > "${TEST_JAVA}/codec/ttlv/${SUB_PATH}/${ENUM_NAME}TtlvTest.java" << EOF
package org.purpleBean.kmip.codec.ttlv.${SUB_PATH//\//.};

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.${SUB_PATH//\//.}.${ENUM_NAME};
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationSuite;

@DisplayName("${ENUM_NAME} TTLV Serialization")
class ${ENUM_NAME}TtlvTest extends AbstractTtlvSerializationSuite<${ENUM_NAME}> {
    @Override
    protected Class<${ENUM_NAME}> type() {
        return ${ENUM_NAME}.class;
    }

    @Override
    protected ${ENUM_NAME} createDefault() {
        return new ${ENUM_NAME}(${ENUM_NAME}.Standard.PLACEHOLDER_1);
    }

    @Override
    protected ${ENUM_NAME} createVariant() {
        return new ${ENUM_NAME}(${ENUM_NAME}.Standard.PLACEHOLDER_2);
    }
}
EOF

# 12. Create Benchmark Subject
cat > "${TEST_JAVA}/benchmark/subjects/${SUB_PATH}/${ENUM_NAME}BenchmarkSubject.java" << EOF
package org.purpleBean.kmip.benchmark.subjects.${SUB_PATH//\//.};

import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.Getter;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.benchmark.util.MapperFactory;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.${SUB_PATH//\//.}.${ENUM_NAME};

import java.nio.ByteBuffer;

public class ${ENUM_NAME}BenchmarkSubject implements KmipBenchmarkSubject {
    private JsonMapper json;
    private XmlMapper xml;
    private TtlvMapper ttlv;

    private ${ENUM_NAME} obj;

    @Getter
    private String jsonStr;
    @Getter
    private String xmlStr;
    @Getter
    private ByteBuffer ttlvBuf;

    public ${ENUM_NAME}BenchmarkSubject() throws Exception {
        this.setup();
    }

    @Override
    public String name() {
        return "${ENUM_NAME}";
    }

    @Override
    public void setup() throws Exception {
        json = MapperFactory.getJsonMapper();
        xml = MapperFactory.getXmlMapper();
        ttlv = MapperFactory.getTtlvMapper();

        obj = new ${ENUM_NAME}(${ENUM_NAME}.Standard.PLACEHOLDER_1);

        // Pre-serialize to ensure all mappers are initialized
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
        return json.readValue(jsonStr, ${ENUM_NAME}.class);
    }

    @Override
    public String xmlSerialize() throws Exception {
        return xml.writeValueAsString(obj);
    }

    @Override
    public Object xmlDeserialize() throws Exception {
        return xml.readValue(xmlStr, ${ENUM_NAME}.class);
    }

    @Override
    public ByteBuffer ttlvSerialize() throws Exception {
        return ttlv.writeValueAsByteBuffer(obj);
    }

    @Override
    public Object ttlvDeserialize() throws Exception {
        return ttlv.readValue(ttlvBuf.duplicate(), ${ENUM_NAME}.class);
    }
}
EOF

# 12. Create ServiceLoader registrations
mkdir -p "src/main/resources/META-INF/services"
mkdir -p "src/test/resources/META-INF/services"

# Function to add and sort service file entries
add_service_entry() {
    local file="$1"
    local entry="$2"
    
    # Create parent directory if it doesn't exist
    mkdir -p "$(dirname "$file")"
    
    # Create file if it doesn't exist
    touch "$file"
    
    # Add the entry if it doesn't exist
    if ! grep -qF "$entry" "$file"; then
        echo "$entry" >> "$file"
    fi
    
    # Sort the file, remove duplicates, and empty lines
    # The following commands ensure:
    # 1. Sort lines alphabetically with -u to remove duplicates
    # 2. Remove empty lines and lines with only whitespace
    # 3. Use a temporary file to avoid issues with reading and writing to the same file
    local temp_file="${file}.tmp"
    sort -u "$file" | grep -v '^[[:space:]]*$' > "$temp_file"
    
    # Only update the file if it changed to preserve timestamps
    if ! cmp -s "$file" "$temp_file"; then
        mv "$temp_file" "$file"
    else
        rm -f "$temp_file"
    fi
}

# Add to JSON serializers
add_service_entry "src/main/resources/META-INF/services/org.purpleBean.kmip.codec.json.serializer.kmip.KmipDataTypeJsonSerializer" \
    "org.purpleBean.kmip.codec.json.serializer.kmip.${SUB_PATH//\//.}.${ENUM_NAME}JsonSerializer"

# Add to JSON deserializers
add_service_entry "src/main/resources/META-INF/services/org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer" \
    "org.purpleBean.kmip.codec.json.deserializer.kmip.${SUB_PATH//\//.}.${ENUM_NAME}JsonDeserializer"

# Add to XML serializers
add_service_entry "src/main/resources/META-INF/services/org.purpleBean.kmip.codec.xml.serializer.kmip.KmipDataTypeXmlSerializer" \
    "org.purpleBean.kmip.codec.xml.serializer.kmip.${SUB_PATH//\//.}.${ENUM_NAME}XmlSerializer"

# Add to XML deserializers
add_service_entry "src/main/resources/META-INF/services/org.purpleBean.kmip.codec.xml.deserializer.kmip.KmipDataTypeXmlDeserializer" \
    "org.purpleBean.kmip.codec.xml.deserializer.kmip.${SUB_PATH//\//.}.${ENUM_NAME}XmlDeserializer"

# Add to TTLV serializers
add_service_entry "src/main/resources/META-INF/services/org.purpleBean.kmip.codec.ttlv.serializer.kmip.KmipDataTypeTtlvSerializer" \
    "org.purpleBean.kmip.codec.ttlv.serializer.kmip.${SUB_PATH//\//.}.${ENUM_NAME}TtlvSerializer"

# Add to TTLV deserializers
add_service_entry "src/main/resources/META-INF/services/org.purpleBean.kmip.codec.ttlv.deserializer.kmip.KmipDataTypeTtlvDeserializer" \
    "org.purpleBean.kmip.codec.ttlv.deserializer.kmip.${SUB_PATH//\//.}.${ENUM_NAME}TtlvDeserializer"

# Add to benchmark subjects
add_service_entry "src/test/resources/META-INF/services/org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject" \
    "org.purpleBean.kmip.benchmark.subjects.${SUB_PATH//\//.}.${ENUM_NAME}BenchmarkSubject"

    echo "Successfully generated files for ${ENUM_NAME}"
    echo "Don't forget to update the Standard enum values in ${ENUM_NAME}.java with actual values from the KMIP specification."
}

# Create directories once
create_directories "${MAIN_JAVA}" "${TEST_JAVA}" "${SUB_PATH}"

# Process each enum name
for enum_name in "${ENUM_NAMES[@]}"; do
    generate_enum "$enum_name"
done

echo -e "\nSuccessfully generated all files for ${#ENUM_NAMES[@]} enums!"

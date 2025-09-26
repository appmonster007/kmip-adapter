#!/bin/bash
# generate_enum.sh
# Refactored generator for KMIP enumerations.
# - Per-file generator functions
# - Flags to enable/disable generation of each artifact
# - Performs a DRY RUN (prints what would be done) when no generation flags provided
# - Compatible with Bash 3.x
set -e

#############################################
# Globals & Defaults
#############################################
BASE_DIR="$(pwd)"
MAIN_JAVA="src/main/java/org/purpleBean/kmip"
TEST_JAVA="src/test/java/org/purpleBean/kmip"
SUB_PATH="common/enumeration"

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
GEN_SERVICES=false

DRY_RUN=false

#############################################
# Helpers
#############################################
usage() {
    cat <<EOF
Usage: $0 [options] <EnumName1> [EnumName2 ...]

If no generation options are provided the script performs a DRY RUN (prints what it would do).
To actually create files pass one or more generation flags.

Options:
  --class         Generate the Enum class
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

Examples:
  # Dry run (no files created)
  $0 State

  # Create only class and json serializer
  $0 --class --json-ser State
EOF
    exit 1
}

# Converts "MyEnum" -> "myEnum"
get_var_name() {
    local name="$1"
    echo "${name:0:1}" | tr '[:upper:]' '[:lower:]'"${name:1}"
}

# Converts "MyEnum" -> "MY_ENUM"
to_snake_upper() {
    local name="$1"
    # Use sed and tr; compatible with older bash
    echo "$name" | sed -r 's/([A-Z])/_\1/g' | sed 's/^_//' | tr '[:lower:]' '[:upper:]'
}

pkg_dot() {
    echo "${SUB_PATH//\//.}"
}

# PascalCase from input tokens: "foo_bar" -> "FooBar"
get_pascal_case() {
    local input="$*"
    echo "$input" | sed -E 's/[_-]+/ /g' | awk '{for(i=1;i<=NF;i++){ $i=toupper(substr($i,1,1)) tolower(substr($i,2)) }}1' | tr -d ' '
}

# Run a command or echo dry-run message
do_or_dry_cmd() {
    local message="$1"; shift
    if [ "${DRY_RUN}" = "true" ]; then
        echo "DRY RUN: ${message}"
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

    if ! grep -qFx "${entry}" "${file}"; then
        echo "${entry}" >> "${file}"
    fi

    local temp_file="${file}.tmp"
    # portable fallback: some platforms may not support redirecting sort -u's output to same file
    if sort -u "${file}" > "${temp_file}" 2>/dev/null; then
        :
    else
        # fallback attempt (should be identical)
        sort -u "${file}" > "${temp_file}"
    fi

    # remove empty lines
    grep -v '^[[:space:]]*$' "${temp_file}" > "${temp_file}.2" && mv "${temp_file}.2" "${temp_file}"

    if ! cmp -s "${file}" "${temp_file}"; 2>/dev/null; then
        # try to move only when different
        mv "${temp_file}" "${file}"
    else
        rm -f "${temp_file}"
    fi
}

#############################################
# Per-artifact generators (each respects DRY_RUN)
#############################################

generate_enum_class() {
    local ENUM_NAME="$1"
    local ENUM_NAME_SNAKE
    ENUM_NAME_SNAKE="$(to_snake_upper "${ENUM_NAME}")"
    local out_dir="${MAIN_JAVA}/${SUB_PATH}"
    local out_file="${out_dir}/${ENUM_NAME}.java"
    local pdot
    pdot="$(pkg_dot)"

    if [ "${DRY_RUN}" = "true" ]; then
        echo "DRY RUN: would create enum class: ${out_file}"
        return 0
    fi

    mkdir -p "${out_dir}"

    cat > "${out_file}" <<EOF
package org.purpleBean.kmip.${pdot};

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
    public static final KmipTag kmipTag = new KmipTag(KmipTag.Standard.${ENUM_NAME_SNAKE});
    public static final EncodingType encodingType = EncodingType.ENUMERATION;
    private static final Map<Integer, Value> VALUE_REGISTRY = new ConcurrentHashMap<>();
    private static final Map<String, Value> DESCRIPTION_REGISTRY = new ConcurrentHashMap<>();
    private static final Map<String, Value> EXTENSION_DESCRIPTION_REGISTRY = new ConcurrentHashMap<>();

    static {
        for (Standard s : Standard.values()) {
            VALUE_REGISTRY.put(s.value, s);
            DESCRIPTION_REGISTRY.put(s.description, s);
        }
    }

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

    @Override
    public KmipTag getKmipTag() {
        return kmipTag;
    }

    @Override
    public EncodingType getEncodingType() {
        return encodingType;
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

    echo "Created: ${out_file}"
}

generate_json_serializer() {
    local ENUM_NAME="$1"
    local out_dir="${MAIN_JAVA}/codec/json/serializer/kmip/${SUB_PATH}"
    local out_file="${out_dir}/${ENUM_NAME}JsonSerializer.java"
    local pdot
    pdot="$(pkg_dot)"
    local enum_snake
    enum_snake="$(to_snake_upper "${ENUM_NAME}")"

    if [ "${DRY_RUN}" = "true" ]; then
        echo "DRY RUN: would create JSON serializer: ${out_file}"
        return 0
    fi

    mkdir -p "${out_dir}"

    cat > "${out_file}" <<EOF
package org.purpleBean.kmip.codec.json.serializer.kmip.${pdot};

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.codec.json.serializer.kmip.KmipDataTypeJsonSerializer;
import org.purpleBean.kmip.${pdot}.${ENUM_NAME};

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

    add_service_entry "src/main/resources/META-INF/services/org.purpleBean.kmip.codec.json.serializer.kmip.KmipDataTypeJsonSerializer" \
        "org.purpleBean.kmip.codec.json.serializer.kmip.${pdot}.${ENUM_NAME}JsonSerializer"

    echo "Created: ${out_file}"
}

generate_json_deserializer() {
    local ENUM_NAME="$1"
    local out_dir="${MAIN_JAVA}/codec/json/deserializer/kmip/${SUB_PATH}"
    local out_file="${out_dir}/${ENUM_NAME}JsonDeserializer.java"
    local pdot
    pdot="$(pkg_dot)"
    local enum_snake
    enum_snake="$(to_snake_upper "${ENUM_NAME}")"
    local enum_lower
    enum_lower="$(echo "${ENUM_NAME}" | tr '[:upper:]' '[:lower:]')"

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
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.${pdot}.${ENUM_NAME};

import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * JSON deserializer for ${ENUM_NAME}.
 */
public class ${ENUM_NAME}JsonDeserializer extends KmipDataTypeJsonDeserializer<${ENUM_NAME}> {
    private final KmipTag kmipTag = ${ENUM_NAME}.kmipTag;
    private final EncodingType encodingType = ${ENUM_NAME}.encodingType;

    @Override
    public ${ENUM_NAME} deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();
        if (node == null) {
            ctxt.reportInputMismatch(${ENUM_NAME}.class, "JSON node cannot be null for ${ENUM_NAME} deserialization");
            return null;
        }

        // Validation: Extract and validate KMIP tag
        KmipTag tag;
        try {
            tag = p.getCodec().treeToValue(node, KmipTag.class);
            if (tag == null) {
                ctxt.reportInputMismatch(${ENUM_NAME}.class, "Invalid KMIP tag for ${ENUM_NAME}");
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
            ctxt.reportInputMismatch(${ENUM_NAME}.class, "Missing or non-text 'type' field for ${ENUM_NAME}");
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
        ${ENUM_NAME}.Value ${enum_lower}Value;
        try {
            ${enum_lower}Value = ${ENUM_NAME}.fromName(spec, description);
        } catch (NoSuchElementException e) {
            ctxt.reportInputMismatch(${ENUM_NAME}.class,
                    String.format("Unknown ${ENUM_NAME} value '%s' for KMIP spec %s", description, spec));
            return null;
        }

        ${ENUM_NAME} ${enum_lower} = new ${ENUM_NAME}(${enum_lower}Value);

        // Final validation: Ensure constructed ${ENUM_NAME} is supported
        if (!${enum_lower}.isSupportedFor(spec)) {
            throw new NoSuchElementException(
                    String.format("${ENUM_NAME} '%s' is not supported for KMIP spec %s", description, spec)
            );
        }

        return ${enum_lower};
    }
}
EOF

    add_service_entry "src/main/resources/META-INF/services/org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer" \
        "org.purpleBean.kmip.codec.json.deserializer.kmip.${pdot}.${ENUM_NAME}JsonDeserializer"

    echo "Created: ${out_file}"
}

generate_xml_serializer() {
    local ENUM_NAME="$1"
    local out_dir="${MAIN_JAVA}/codec/xml/serializer/kmip/${SUB_PATH}"
    local out_file="${out_dir}/${ENUM_NAME}XmlSerializer.java"
    local pdot
    pdot="$(pkg_dot)"
    local enum_snake
    enum_snake="$(to_snake_upper "${ENUM_NAME}")"

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
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.codec.xml.serializer.kmip.KmipDataTypeXmlSerializer;
import org.purpleBean.kmip.${pdot}.${ENUM_NAME};

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

    add_service_entry "src/main/resources/META-INF/services/org.purpleBean.kmip.codec.xml.serializer.kmip.KmipDataTypeXmlSerializer" \
        "org.purpleBean.kmip.codec.xml.serializer.kmip.${pdot}.${ENUM_NAME}XmlSerializer"

    echo "Created: ${out_file}"
}

generate_xml_deserializer() {
    local ENUM_NAME="$1"
    local out_dir="${MAIN_JAVA}/codec/xml/deserializer/kmip/${SUB_PATH}"
    local out_file="${out_dir}/${ENUM_NAME}XmlDeserializer.java"
    local pdot
    pdot="$(pkg_dot)"
    local enum_snake
    enum_snake="$(to_snake_upper "${ENUM_NAME}")"
    local enum_lower
    enum_lower="$(echo "${ENUM_NAME}" | tr '[:upper:]' '[:lower:]')"

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
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.xml.deserializer.kmip.KmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.${pdot}.${ENUM_NAME};

import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * XML deserializer for ${ENUM_NAME}.
 */
public class ${ENUM_NAME}XmlDeserializer extends KmipDataTypeXmlDeserializer<${ENUM_NAME}> {
    private final KmipTag kmipTag = ${ENUM_NAME}.kmipTag;
    private final EncodingType encodingType = ${ENUM_NAME}.encodingType;

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

        ${ENUM_NAME} ${enum_lower} = new ${ENUM_NAME}(${ENUM_NAME}.fromName(spec, description));
        if (!${enum_lower}.isSupportedFor(spec)) {
            throw new NoSuchElementException(
                String.format("${ENUM_NAME} '%s' not supported for spec %s", description, spec));
        }

        return ${enum_lower};
    }
}
EOF

    add_service_entry "src/main/resources/META-INF/services/org.purpleBean.kmip.codec.xml.deserializer.kmip.KmipDataTypeXmlDeserializer" \
        "org.purpleBean.kmip.codec.xml.deserializer.kmip.${pdot}.${ENUM_NAME}XmlDeserializer"

    echo "Created: ${out_file}"
}

generate_ttlv_serializer() {
    local ENUM_NAME="$1"
    local out_dir="${MAIN_JAVA}/codec/ttlv/serializer/kmip/${SUB_PATH}"
    local out_file="${out_dir}/${ENUM_NAME}TtlvSerializer.java"
    local pdot
    pdot="$(pkg_dot)"

    if [ "${DRY_RUN}" = "true" ]; then
        echo "DRY RUN: would create TTLV serializer: ${out_file}"
        return 0
    fi

    mkdir -p "${out_dir}"

    cat > "${out_file}" <<EOF
package org.purpleBean.kmip.codec.ttlv.serializer.kmip.${pdot};

import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.codec.ttlv.TtlvObject;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.codec.ttlv.serializer.kmip.KmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.${pdot}.${ENUM_NAME};

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

    add_service_entry "src/main/resources/META-INF/services/org.purpleBean.kmip.codec.ttlv.serializer.kmip.KmipDataTypeTtlvSerializer" \
        "org.purpleBean.kmip.codec.ttlv.serializer.kmip.${pdot}.${ENUM_NAME}TtlvSerializer"

    echo "Created: ${out_file}"
}

generate_ttlv_deserializer() {
    local ENUM_NAME="$1"
    local out_dir="${MAIN_JAVA}/codec/ttlv/deserializer/kmip/${SUB_PATH}"
    local out_file="${out_dir}/${ENUM_NAME}TtlvDeserializer.java"
    local pdot
    pdot="$(pkg_dot)"
    local enum_snake
    enum_snake="$(to_snake_upper "${ENUM_NAME}")"
    local enum_lower
    enum_lower="$(echo "${ENUM_NAME}" | tr '[:upper:]' '[:lower:]')"

    if [ "${DRY_RUN}" = "true" ]; then
        echo "DRY RUN: would create TTLV deserializer: ${out_file}"
        return 0
    fi

    mkdir -p "${out_dir}"

    cat > "${out_file}" <<EOF
package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.${pdot};

import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.ttlv.TtlvConstants;
import org.purpleBean.kmip.codec.ttlv.TtlvObject;
import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.KmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.${pdot}.${ENUM_NAME};

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * TTLV deserializer for ${ENUM_NAME}.
 */
public class ${ENUM_NAME}TtlvDeserializer extends KmipDataTypeTtlvDeserializer<${ENUM_NAME}> {
    private final KmipTag kmipTag = ${ENUM_NAME}.kmipTag;
    private final EncodingType encodingType = ${ENUM_NAME}.encodingType;

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
        ${ENUM_NAME} ${enum_lower} = new ${ENUM_NAME}(${ENUM_NAME}.fromValue(spec, value));

        if (!${enum_lower}.isSupportedFor(spec)) {
            throw new NoSuchElementException();
        }
        return ${enum_lower};
    }
}
EOF

    add_service_entry "src/main/resources/META-INF/services/org.purpleBean.kmip.codec.ttlv.deserializer.kmip.KmipDataTypeTtlvDeserializer" \
            "org.purpleBean.kmip.codec.ttlv.deserializer.kmip.${pdot}.${ENUM_NAME}TtlvDeserializer"

    echo "Created: ${out_file}"
}

generate_domain_test() {
    local ENUM_NAME="$1"
    local out_dir="${TEST_JAVA}/${SUB_PATH}"
    local out_file="${out_dir}/${ENUM_NAME}Test.java"
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
        ${ENUM_NAME}.Value custom = ${ENUM_NAME}.register(0x80000010, "X-Enum-Custom", Set.of(KmipSpec.UnknownVersion));
        assertThat(custom.isCustom()).isTrue();
        assertThat(custom.getDescription()).isEqualTo("X-Enum-Custom");
        assertThat(custom.isSupportedFor(KmipSpec.UnknownVersion)).isTrue();
        assertThat(custom.isSupportedFor(KmipSpec.UnsupportedVersion)).isFalse();

        // Negative cases: invalid range, empty description, empty versions
        assertThatThrownBy(() -> ${ENUM_NAME}.register(0x7FFFFFFF, "Bad-Range", Set.of(KmipSpec.UnknownVersion)))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> ${ENUM_NAME}.register(0x00000001, "Bad-Range", Set.of(KmipSpec.UnknownVersion)))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> ${ENUM_NAME}.register(0x80000011, "   ", Set.of(KmipSpec.UnknownVersion)))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> ${ENUM_NAME}.register(0x80000012, "X-Empty-Versions", Set.of()))
                .isInstanceOf(IllegalArgumentException.class);
    }
}

EOF

    echo "Created: ${out_file}"
}

generate_serialization_test_for_format() {
    local ENUM_NAME="$1"
    local format="$2"
    local format_pascal
    format_pascal="$(get_pascal_case "${format}")"
    local suite_name="${ENUM_NAME}${format_pascal}Test"
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
import org.purpleBean.kmip.${pdot}.${ENUM_NAME};
import org.purpleBean.kmip.test.suite.Abstract${format_pascal}SerializationSuite;

@DisplayName("${ENUM_NAME} ${format} Serialization")
class ${ENUM_NAME}${format_pascal}Test extends Abstract${format_pascal}SerializationSuite<${ENUM_NAME}> {
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

    echo "Created: ${out_file}"
}

generate_benchmark_subject() {
    local ENUM_NAME="$1"
    local out_dir="${TEST_JAVA}/benchmark/subjects/${SUB_PATH}"
    local out_file="${out_dir}/${ENUM_NAME}BenchmarkSubject.java"
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
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.benchmark.util.MapperFactory;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.${pdot}.${ENUM_NAME};

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

    add_service_entry "src/test/resources/META-INF/services/org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject" \
        "org.purpleBean.kmip.benchmark.subjects.${pdot}.${ENUM_NAME}BenchmarkSubject"

    echo "Created: ${out_file}"
}

#############################################
# Orchestrator for a single enum
#############################################
generate_enum() {
    local ENUM_NAME="$1"
    echo
    echo "Processing enum: ${ENUM_NAME}"

    ${GEN_CLASS} && generate_enum_class "${ENUM_NAME}"
    ${GEN_JSON_SER} && generate_json_serializer "${ENUM_NAME}"
    ${GEN_JSON_DES} && generate_json_deserializer "${ENUM_NAME}"
    ${GEN_XML_SER} && generate_xml_serializer "${ENUM_NAME}"
    ${GEN_XML_DES} && generate_xml_deserializer "${ENUM_NAME}"
    ${GEN_TTLV_SER} && generate_ttlv_serializer "${ENUM_NAME}"
    ${GEN_TTLV_DES} && generate_ttlv_deserializer "${ENUM_NAME}"
    ${GEN_DOMAIN_TEST} && generate_domain_test "${ENUM_NAME}"
    ${GEN_JSON_TEST} && generate_serialization_test_for_format "${ENUM_NAME}" "json"
    ${GEN_XML_TEST} && generate_serialization_test_for_format "${ENUM_NAME}" "xml"
    ${GEN_TTLV_TEST} && generate_serialization_test_for_format "${ENUM_NAME}" "ttlv"
    ${GEN_BENCHMARK} && generate_benchmark_subject "${ENUM_NAME}"

    echo "Finished (or planned) generation for ${ENUM_NAME}"
    echo "Remember to update Standard values in ${ENUM_NAME}.java with real KMIP enum values."
}

#############################################
# Main
#############################################
if [ $# -eq 0 ]; then
    usage
fi

ENUMS=()
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
            GEN_TTLV_TEST=true; GEN_BENCHMARK=true; GEN_SERVICES=true;
            any_flag=true; shift ;;
        -h|--help) usage ;;
        --*) echo "Unknown option: $1"; usage ;;
        *) ENUMS[${#ENUMS[@]}]="$1"; shift ;;
    esac
done

if [ ${#ENUMS[@]} -eq 0 ]; then
    echo "Error: at least one enum name required."
    usage
fi

# If no flags provided -> dry run and plan everything
if [ "${any_flag}" = "false" ]; then
    DRY_RUN=true
    echo "No generation flags provided -> performing DRY RUN (no files will be written)."
    GEN_CLASS=true; GEN_JSON_SER=true; GEN_JSON_DES=true; GEN_XML_SER=true; GEN_XML_DES=true
    GEN_TTLV_SER=true; GEN_TTLV_DES=true; GEN_DOMAIN_TEST=true; GEN_JSON_TEST=true; GEN_XML_TEST=true
    GEN_TTLV_TEST=true; GEN_BENCHMARK=true; GEN_SERVICES=true
fi

# Prepare directories (dry-run will only print)
create_directories "${MAIN_JAVA}" "${TEST_JAVA}" "${SUB_PATH}"

# Process each enum
i=0
while [ "${i}" -lt "${#ENUMS[@]}" ]; do
    enum_name="${ENUMS[$i]}"
    generate_enum "${enum_name}"
    i=$((i + 1))
done

if [ "${DRY_RUN}" = "true" ]; then
    echo
    echo "DRY RUN complete. Nothing was written."
    echo "Re-run with flags (e.g. --all) to actually generate files."
else
    echo
    echo "Generation complete for ${#ENUMS[@]} enum(s)."
    echo "Don't forget to fill in real enum values and review generated TODOs."
fi

exit 0

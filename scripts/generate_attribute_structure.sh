#!/bin/bash
# generate_attribute_structure.sh

set -e

#############################################
# Defaults / Globals
#############################################
BASE_DIR="$(pwd)"
MAIN_JAVA="src/main/java/org/purpleBean/kmip"
TEST_JAVA="src/test/java/org/purpleBean/kmip"
SUB_PATH="common/structure"

# Generation flags (default off)
GEN_DOMAIN=false
GEN_TEST=false
GEN_JSON_SER=false
GEN_JSON_DES=false
GEN_XML_SER=false
GEN_XML_DES=false
GEN_TTLV_SER=false
GEN_TTLV_DES=false
GEN_SERIAL_TEST=false
GEN_BENCHMARK=false
GEN_SERVICES=false

# Dry run toggled automatically when no flags specified
DRY_RUN=false

#############################################
# Helpers (bash-3-compatible)
#############################################
usage() {
    cat <<EOF
Usage: $0 [options] <StructureName1> [StructureName2 ...]

If no generation options are provided the script performs a DRY RUN (prints what it would do).
To actually write files supply one or more generation flags.

Options:
  --domain        Generate domain class (structure)
  --test          Generate domain unit test
  --json-ser      Generate JSON serializer
  --json-des      Generate JSON deserializer
  --xml-ser       Generate XML serializer
  --xml-des       Generate XML deserializer
  --ttlv-ser      Generate TTLV serializer
  --ttlv-des      Generate TTLV deserializer
  --serial-test   Generate serialization tests (json,xml,ttlv)
  --benchmark     Generate benchmark subject
  --services      Add service entries to META-INF files
  --all           Generate everything
  -h, --help      Show this help

Examples:
  # Dry run (no files will be written)
  $0 CustomAttribute

  # Generate everything for two structures
  $0 --all CustomAttribute SecurityAttribute
EOF
    exit 1
}

# camelCase from PascalCase (or leave if already camelCase)
get_camel_case() {
    local input="$1"
    local first_char="$(echo "${input}" | cut -c1)"
    local lower_first
    lower_first="$(echo "${first_char}" | tr '[:upper:]' '[:lower:]')"
    if [ "${lower_first}" = "${first_char}" ]; then
        echo "${input}"
    else
        echo "${lower_first}${input:1}"
    fi
}

# PascalCase from strings (space/underscore/dash separated)
get_pascal_case() {
    # join all args
    local input="$*"
    # convert separators to spaces, then uppercase first letter of each word and remove spaces
    echo "${input}" | sed -E 's/[_-]/ /g' | awk '{
        for(i=1;i<=NF;i++){
            $i = toupper(substr($i,1,1)) tolower(substr($i,2))
        }
        printf "%s", $1
        for(j=2;j<=NF;j++) printf "%s", $j
    }'
}

# Convert PascalCase -> SNAKE_UPPER
to_snake_upper() {
    local name="$1"
    # insert underscore before each uppercase (except start), then uppercase
    echo "$name" | sed -r 's/([A-Z])/_\1/g' | sed 's/^_//' | tr '[:lower:]' '[:upper:]'
}

# make dotted package path from slash path
slash_to_dot() {
    local s="$1"
    echo "${s//\//.}"
}

#############################################
# FS & service helpers (respect DRY_RUN)
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

    for dir in serializer deserializer; do
        mkdir -p "${main_java}/codec/json/${dir}/kmip/${sub_path}"
        mkdir -p "${main_java}/codec/xml/${dir}/kmip/${sub_path}"
        mkdir -p "${main_java}/codec/ttlv/${dir}/kmip/${sub_path}"
    done

    mkdir -p "${test_java}/${sub_path}"
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

    # only add exact line if not present
    if ! grep -qFx "${entry}" "${file}"; then
        echo "${entry}" >> "${file}"
        # sort & unique in-place (sort -u -o available on many systems)
        sort -u "${file}" -o "${file}" 2>/dev/null || {
            # fallback if sort -o not supported
            sort -u "${file}" > "${file}.tmp" && mv "${file}.tmp" "${file}"
        }
        echo "Added service entry: ${entry} -> ${file}"
    else
        echo "Service entry already present: ${entry} in ${file}"
    fi
}

register_services() {
    local STRUCT_NAME="$1"
    local PACKAGE_DOT="$2"

    if [ "${GEN_SERVICES}" = "false" ]; then
        return 0
    fi

    add_service_entry "src/main/resources/META-INF/services/org.purpleBean.kmip.codec.json.serializer.kmip.KmipDataTypeJsonSerializer" \
        "org.purpleBean.kmip.codec.json.serializer.kmip.${PACKAGE_DOT}.${STRUCT_NAME}JsonSerializer"

    add_service_entry "src/main/resources/META-INF/services/org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer" \
        "org.purpleBean.kmip.codec.json.deserializer.kmip.${PACKAGE_DOT}.${STRUCT_NAME}JsonDeserializer"

    add_service_entry "src/main/resources/META-INF/services/org.purpleBean.kmip.codec.xml.serializer.kmip.KmipDataTypeXmlSerializer" \
        "org.purpleBean.kmip.codec.xml.serializer.kmip.${PACKAGE_DOT}.${STRUCT_NAME}XmlSerializer"

    add_service_entry "src/main/resources/META-INF/services/org.purpleBean.kmip.codec.xml.deserializer.kmip.KmipDataTypeXmlDeserializer" \
        "org.purpleBean.kmip.codec.xml.deserializer.kmip.${PACKAGE_DOT}.${STRUCT_NAME}XmlDeserializer"

    add_service_entry "src/main/resources/META-INF/services/org.purpleBean.kmip.codec.ttlv.serializer.kmip.KmipDataTypeTtlvSerializer" \
        "org.purpleBean.kmip.codec.ttlv.serializer.kmip.${PACKAGE_DOT}.${STRUCT_NAME}TtlvSerializer"

    add_service_entry "src/main/resources/META-INF/services/org.purpleBean.kmip.codec.ttlv.deserializer.kmip.KmipDataTypeTtlvDeserializer" \
        "org.purpleBean.kmip.codec.ttlv.deserializer.kmip.${PACKAGE_DOT}.${STRUCT_NAME}TtlvDeserializer"

    add_service_entry "src/test/resources/META-INF/services/org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject" \
        "org.purpleBean.kmip.benchmark.subjects.${PACKAGE_DOT}.${STRUCT_NAME}BenchmarkSubject"
}

#############################################
# Generators (each checks DRY_RUN)
#############################################

generate_domain_class() {
    local class_name="$1"
    local package_path="$2"
    local class_snake
    class_snake=$(echo "${class_name}" | sed -r 's/([a-z0-9])([A-Z])/\1_\2/g' | tr '[:lower:]' '[:upper:]')
    local out_dir="${MAIN_JAVA}/${package_path}"
    local out_file="${out_dir}/${class_name}.java"
    local pkg_dot
    pkg_dot=$(slash_to_dot "${package_path}")

    if [ "${DRY_RUN}" = "true" ]; then
        echo "DRY RUN: would create domain class: ${out_file}"
        return 0
    fi

    mkdir -p "${out_dir}"

    cat > "${out_file}" <<EOF
package org.purpleBean.kmip.${pkg_dot};

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

    private final KmipTag kmipTag = new KmipTag(KmipTag.Standard.${class_snake});
    private final EncodingType encodingType = EncodingType.STRUCTURE;
    private final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion);

    private final boolean alwaysPresent = false;
    private final boolean serverInitializable = false;
    private final boolean clientInitializable = false;
    private final boolean clientDeletable = false;
    private final boolean multiInstanceAllowed = false;

    // TODO: Add your structure fields here
    @NonNull
    private final ActivationDateAttribute activationDate;
    private final State state;

    @Override
    public List<KmipDataType> getValues() {
        List<KmipDataType> values = new ArrayList<KmipDataType>();
        values.add(activationDate);
        if (state != null) {
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
        return state.getValue().getValue() == State.Standard.ACTIVE.getValue();
    }

    @Override
    public boolean isClientModifiable(State state) {
        return state.getValue().getValue() == State.Standard.ACTIVE.getValue();
    }

    public static class ${class_name}Builder {
        public ${class_name} build() {
            validate();
            // NOTE: This builder body may need actual constructor parameters
            return new ${class_name}(/* TODO: constructor parameters */);
        }

        private void validate() {
            KmipSpec spec = KmipContext.getSpec();
            // Add validations if needed
        }
    }
}
EOF

    echo "Created: ${out_file}"
}

generate_domain_test() {
    local class_name="$1"
    local package_path="$2"
    local out_dir="${TEST_JAVA}/${package_path}"
    local out_file="${out_dir}/${class_name}Test.java"
    local pkg_dot
    pkg_dot=$(slash_to_dot "${package_path}")
    local class_snake
    class_snake=$(echo "${class_name}" | sed -r 's/([a-z0-9])([A-Z])/\1_\2/g' | tr '[:lower:]' '[:upper:]')

    if [ "${DRY_RUN}" = "true" ]; then
        echo "DRY RUN: would create domain test: ${out_file}"
        return 0
    fi

    mkdir -p "${out_dir}"

    cat > "${out_file}" <<EOF
package org.purpleBean.kmip.${pkg_dot};

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
            .activationDate(ActivationDateAttribute.builder().dateTime(OffsetDateTime.now()).build())
            .build();
    }

    @Override
    protected boolean expectAlwaysPresent() {
        return false;
    }
    @Override
    protected boolean expectServerInitializable() { return false; }
    @Override
    protected boolean expectClientInitializable() { return false; }
    @Override
    protected boolean expectClientDeletable() { return false; }
    @Override
    protected boolean expectMultiInstanceAllowed() { return false; }

    @Override
    protected State stateForServerModifiableTrue() { return new State(State.Standard.ACTIVE); }
    @Override
    protected State stateForServerModifiableFalse() { return new State(State.Standard.DEACTIVATED); }
    @Override
    protected State stateForClientModifiableTrue() { return new State(State.Standard.ACTIVE); }
    @Override
    protected State stateForClientModifiableFalse() { return new State(State.Standard.DEACTIVATED); }

    @Test
    @DisplayName("should have correct KMIP tag")
    void shouldHaveCorrectKmipTag() {
        ${class_name} attr = createDefault();
        assertThat(attr.getKmipTag().getValue()).isEqualTo(KmipTag.Standard.${class_snake});
    }
}
EOF

    echo "Created: ${out_file}"
}

generate_json_serializer() {
    local class_name="$1"
    local package_path="$2"
    local out_dir="${MAIN_JAVA}/codec/json/serializer/kmip/${package_path}"
    local out_file="${out_dir}/${class_name}JsonSerializer.java"
    local pkg_dot
    pkg_dot=$(slash_to_dot "${package_path}")
    local class_lower
    class_lower=$(get_camel_case "${class_name}")

    if [ "${DRY_RUN}" = "true" ]; then
        echo "DRY RUN: would create JSON serializer: ${out_file}"
        return 0
    fi

    mkdir -p "${out_dir}"

    cat > "${out_file}" <<EOF
package org.purpleBean.kmip.codec.json.serializer.kmip.${pkg_dot};

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.codec.json.serializer.kmip.KmipDataTypeJsonSerializer;
import org.purpleBean.kmip.${pkg_dot}.${class_name};

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

public class ${class_name}JsonSerializer extends KmipDataTypeJsonSerializer<${class_name}> {

    @Override
    public void serialize(${class_name} ${class_lower}, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (${class_lower} == null) {
            return;
        }

        KmipSpec spec = KmipContext.getSpec();
        if (!${class_lower}.isSupportedFor(spec)) {
            throw new UnsupportedEncodingException(
                String.format("%s is not supported for KMIP spec %s", ${class_lower}.getClass().getSimpleName(), spec)
            );
        }

        List<KmipDataType> fields = ${class_lower}.getValues();
        for (KmipDataType field : fields) {
            if (field != null && !field.isSupportedFor(spec)) {
                throw new UnsupportedEncodingException(
                    String.format("%s in %s is not supported for KMIP spec %s",
                        field.getKmipTag().getDescription(), ${class_lower}.getClass().getSimpleName(), spec)
                );
            }
        }

        jsonGenerator.writeStartObject();
        jsonGenerator.writeObject(${class_lower}.getKmipTag());
        jsonGenerator.writeStringField("type", ${class_lower}.getEncodingType().getDescription());
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

    echo "Created: ${out_file}"
}

generate_json_deserializer() {
    local class_name="$1"
    local package_path="$2"
    local out_dir="${MAIN_JAVA}/codec/json/deserializer/kmip/${package_path}"
    local out_file="${out_dir}/${class_name}JsonDeserializer.java"
    local pkg_dot
    pkg_dot=$(slash_to_dot "${package_path}")
    local class_lower
    class_lower=$(get_camel_case "${class_name}")
    local class_snake
    class_snake=$(echo "${class_name}" | sed -r 's/([a-z0-9])([A-Z])/\1_\2/g' | tr '[:lower:]' '[:upper:]')

    if [ "${DRY_RUN}" = "true" ]; then
        echo "DRY RUN: would create JSON deserializer: ${out_file}"
        return 0
    fi

    mkdir -p "${out_dir}"

    cat > "${out_file}" <<EOF
package org.purpleBean.kmip.codec.json.deserializer.kmip.${pkg_dot};

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.${pkg_dot}.${class_name};

import java.io.IOException;
import java.util.NoSuchElementException;

public class ${class_name}JsonDeserializer extends KmipDataTypeJsonDeserializer<${class_name}> {
    private final KmipTag kmipTag = new KmipTag(KmipTag.Standard.${class_snake});
    private final EncodingType encodingType = EncodingType.STRUCTURE;

    @Override
    public ${class_name} deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();
        if (node == null) {
            ctxt.reportInputMismatch(${class_name}.class, "JSON node cannot be null for ${class_name} deserialization");
            return null;
        }

        KmipTag tag;
        try {
            tag = p.getCodec().treeToValue(node, KmipTag.class);
            if (tag == null) {
                ctxt.reportInputMismatch(${class_name}.class, "Invalid KMIP tag for ${class_name}");
                return null;
            }
        } catch (Exception e) {
            ctxt.reportInputMismatch(${class_name}.class, String.format("Failed to parse KMIP tag for ${class_name}: %s", e.getMessage()));
            return null;
        }

        if (!node.isObject() || tag.getValue().getValue() != kmipTag.getValue().getValue()) {
            ctxt.reportInputMismatch(${class_name}.class, "Expected object for ${class_name}");
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() || !encodingType.getDescription().equals(typeNode.asText())) {
            ctxt.reportInputMismatch(${class_name}.class, String.format("Invalid or missing type field for ${class_name}, expected: %s", encodingType.getDescription()));
            return null;
        }

        JsonNode valuesNode = node.get("value");
        if (valuesNode == null || !valuesNode.isArray()) {
            ctxt.reportInputMismatch(${class_name}.class, "Missing or invalid 'value' array for ${class_name}");
            return null;
        }

        ${class_name}.${class_name}Builder builder = ${class_name}.builder();

        for (JsonNode valueNode : valuesNode) {
            if (valueNode == null || !valueNode.has("tag")) {
                continue;
            }
            try {
                KmipTag.Value nodeTag = p.getCodec().treeToValue(valueNode, KmipTag.class).getValue();
                setValue(builder, nodeTag, valueNode, p, ctxt);
            } catch (Exception e) {
                ctxt.reportInputMismatch(${class_name}.class, String.format("Failed to process field in ${class_name}: %s", e.getMessage()));
                return null;
            }
        }

        ${class_name} ${class_lower} = builder.build();

        if (!${class_lower}.isSupportedFor(KmipContext.getSpec())) {
            throw new NoSuchElementException(String.format("${class_name} is not supported for KMIP spec %s", KmipContext.getSpec()));
        }

        return ${class_lower};
    }

    protected void setValue(${class_name}.${class_name}Builder builder,
                          KmipTag.Value nodeTag,
                          JsonNode node,
                          JsonParser p,
                          DeserializationContext ctxt) throws IOException {
        throw new UnsupportedOperationException("Field deserialization not implemented for tag: " + nodeTag);
    }
}
EOF

    echo "Created: ${out_file}"
}

generate_xml_serializer() {
    local class_name="$1"
    local package_path="$2"
    local out_dir="${MAIN_JAVA}/codec/xml/serializer/kmip/${package_path}"
    local out_file="${out_dir}/${class_name}XmlSerializer.java"
    local pkg_dot
    pkg_dot=$(slash_to_dot "${package_path}")
    local class_lower
    class_lower=$(get_camel_case "${class_name}")

    if [ "${DRY_RUN}" = "true" ]; then
        echo "DRY RUN: would create XML serializer: ${out_file}"
        return 0
    fi

    mkdir -p "${out_dir}"

    cat > "${out_file}" <<EOF
package org.purpleBean.kmip.codec.xml.serializer.kmip.${pkg_dot};

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.codec.xml.serializer.kmip.KmipDataTypeXmlSerializer;
import org.purpleBean.kmip.${pkg_dot}.${class_name};

import javax.xml.namespace.QName;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

public class ${class_name}XmlSerializer extends KmipDataTypeXmlSerializer<${class_name}> {

    @Override
    public void serialize(${class_name} ${class_lower}, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        KmipSpec spec = KmipContext.getSpec();
        if (!${class_lower}.isSupportedFor(spec)) {
            throw new UnsupportedEncodingException(String.format("%s not supported for KMIP spec %s", ${class_lower}.getClass().getSimpleName(), spec));
        }

        if (!(gen instanceof ToXmlGenerator)) {
            throw new IllegalStateException("Expected ToXmlGenerator");
        }

        String elementName = ${class_lower}.getKmipTag().getDescription();
        ((ToXmlGenerator) gen).setNextName(QName.valueOf(elementName));
        gen.writeStartObject(${class_lower});

        List<KmipDataType> values = ${class_lower}.getValues();
        for (KmipDataType kmipDataType : values) {
            if (kmipDataType != null && kmipDataType.getKmipTag() != null) {
                serializers.defaultSerializeField(kmipDataType.getKmipTag().getDescription(), kmipDataType, gen);
            }
        }

        gen.writeEndObject();
    }
}
EOF

    echo "Created: ${out_file}"
}

generate_xml_deserializer() {
    local class_name="$1"
    local package_path="$2"
    local out_dir="${MAIN_JAVA}/codec/xml/deserializer/kmip/${package_path}"
    local out_file="${out_dir}/${class_name}XmlDeserializer.java"
    local pkg_dot
    pkg_dot=$(slash_to_dot "${package_path}")
    local class_lower
    class_lower=$(get_camel_case "${class_name}")
    local class_snake
    class_snake=$(echo "${class_name}" | sed -r 's/([a-z0-9])([A-Z])/\1_\2/g' | tr '[:lower:]' '[:upper:]')

    if [ "${DRY_RUN}" = "true" ]; then
        echo "DRY RUN: would create XML deserializer: ${out_file}"
        return 0
    fi

    mkdir -p "${out_dir}"

    cat > "${out_file}" <<EOF
package org.purpleBean.kmip.codec.xml.deserializer.kmip.${pkg_dot};

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.dataformat.xml.deser.FromXmlParser;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.codec.xml.deserializer.kmip.KmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.${pkg_dot}.${class_name};

import java.io.IOException;
import java.util.Map;
import java.util.NoSuchElementException;

public class ${class_name}XmlDeserializer extends KmipDataTypeXmlDeserializer<${class_name}> {
    private final KmipTag kmipTag = new KmipTag(KmipTag.Standard.${class_snake});
    private final EncodingType encodingType = EncodingType.STRUCTURE;

    @Override
    public ${class_name} deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(${class_name}.class, "Expected XML object for ${class_name}");
            return null;
        }

        if (p instanceof FromXmlParser && !kmipTag.getDescription().equalsIgnoreCase(((FromXmlParser)p).getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(${class_name}.class, "Invalid Tag for ${class_name}");
            return null;
        }

        KmipSpec spec = KmipContext.getSpec();
        ${class_name}.${class_name}Builder builder = ${class_name}.builder();

        java.util.Iterator<java.util.Map.Entry<String, JsonNode>> fields = node.fields();
        while (fields.hasNext()) {
            Map.Entry<String, JsonNode> entry = fields.next();
            KmipTag.Value nodeTag = KmipTag.fromName(spec, entry.getKey());
            setValue(builder, nodeTag, entry.getValue(), p, ctxt);
        }

        ${class_name} ${class_lower} = builder.build();
        if (!${class_lower}.isSupportedFor(spec)) {
            throw new NoSuchElementException(String.format("${class_name} is not supported for KMIP spec %s", spec));
        }
        return ${class_lower};
    }

    protected void setValue(${class_name}.${class_name}Builder builder,
                          KmipTag.Value nodeTag,
                          JsonNode node,
                          JsonParser p,
                          DeserializationContext ctxt) throws IOException {
        throw new UnsupportedOperationException("Field deserialization not implemented for tag: " + nodeTag);
    }
}
EOF

    echo "Created: ${out_file}"
}

generate_ttlv_serializer() {
    local class_name="$1"
    local package_path="$2"
    local out_dir="${MAIN_JAVA}/codec/ttlv/serializer/kmip/${package_path}"
    local out_file="${out_dir}/${class_name}TtlvSerializer.java"
    local pkg_dot
    pkg_dot=$(slash_to_dot "${package_path}")

    if [ "${DRY_RUN}" = "true" ]; then
        echo "DRY RUN: would create TTLV serializer: ${out_file}"
        return 0
    fi

    mkdir -p "${out_dir}"

    cat > "${out_file}" <<EOF
package org.purpleBean.kmip.codec.ttlv.serializer.kmip.${pkg_dot};

import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipDataType;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.codec.ttlv.TtlvObject;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.codec.ttlv.serializer.kmip.KmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.${pkg_dot}.${class_name};

import java.io.IOException;
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
            throw new IOException(String.format("%s not supported for KMIP spec %s", value.getClass().getSimpleName(), spec));
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

        int totalLength = 0;
        for (ByteBuffer b : nestedObjects) totalLength += b.remaining();
        ByteBuffer payloadBuffer = ByteBuffer.allocate(totalLength);
        for (ByteBuffer b : nestedObjects) payloadBuffer.put(b);

        byte[] payload = payloadBuffer.array();

        return TtlvObject.builder().tag(tag).type(type).value(payload).build();
    }
}
EOF

    echo "Created: ${out_file}"
}

generate_ttlv_deserializer() {
    local class_name="$1"
    local package_path="$2"
    local out_dir="${MAIN_JAVA}/codec/ttlv/deserializer/kmip/${package_path}"
    local out_file="${out_dir}/${class_name}TtlvDeserializer.java"
    local pkg_dot
    pkg_dot=$(slash_to_dot "${package_path}")
    local class_snake
    class_snake=$(echo "${class_name}" | sed -r 's/([a-z0-9])([A-Z])/\1_\2/g' | tr '[:lower:]' '[:upper:]')
    local class_lower
    class_lower=$(get_camel_case "${class_name}")

    if [ "${DRY_RUN}" = "true" ]; then
        echo "DRY RUN: would create TTLV deserializer: ${out_file}"
        return 0
    fi

    mkdir -p "${out_dir}"

    cat > "${out_file}" <<EOF
package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.${pkg_dot};

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
import org.purpleBean.kmip.${pkg_dot}.${class_name};

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

public class ${class_name}TtlvDeserializer extends KmipDataTypeTtlvDeserializer<${class_name}> {
    private final EncodingType type = EncodingType.STRUCTURE;
    private final KmipTag kmipTag = new KmipTag(KmipTag.Standard.${class_snake});

    @Override
    public ${class_name} deserialize(ByteBuffer ttlvBuffer, TtlvMapper mapper) throws IOException {
        TtlvObject obj = TtlvObject.fromBuffer(ttlvBuffer);
        if (Arrays.equals(obj.getTag(), kmipTag.getTagBytes()) && obj.getType() != type.getTypeValue()) {
            throw new IllegalArgumentException(String.format("Expected %s type for %s, got %s", type.getTypeValue(), kmipTag.getDescription(), obj.getType()));
        }

        List<TtlvObject> nestedObjects = TtlvObject.fromBytesMultiple(obj.getValue());
        KmipSpec spec = KmipContext.getSpec();
        ${class_name}.${class_name}Builder builder = ${class_name}.builder();

        for (TtlvObject ttlvObject : nestedObjects) {
            KmipTag.Value nodeTag = KmipTag.fromBytes(spec, ttlvObject.getTag());
            setValue(builder, nodeTag, ttlvObject, mapper);
        }

        ${class_name} ${class_lower} = builder.build();
        if (!${class_lower}.isSupportedFor(spec)) {
            throw new NoSuchElementException(String.format("%s is not supported for KMIP spec %s", ${class_lower}.getClass().getSimpleName(), spec));
        }
        return ${class_lower};
    }

    private void setValue(${class_name}.${class_name}Builder builder,
                         KmipTag.Value nodeTag,
                         TtlvObject ttlvObject,
                         TtlvMapper mapper) throws IOException {
        throw new UnsupportedOperationException("Field TTLV deserialization not implemented for tag: " + nodeTag);
    }
}
EOF

    echo "Created: ${out_file}"
}

generate_benchmark_subject() {
    local class_name="$1"
    local package_path="$2"
    local out_dir="${TEST_JAVA}/benchmark/subjects/${package_path}"
    local out_file="${out_dir}/${class_name}BenchmarkSubject.java"
    local pkg_dot
    pkg_dot=$(slash_to_dot "${package_path}")

    if [ "${DRY_RUN}" = "true" ]; then
        echo "DRY RUN: would create benchmark subject: ${out_file}"
        return 0
    fi

    mkdir -p "${out_dir}"

    cat > "${out_file}" <<EOF
package org.purpleBean.kmip.benchmark.subjects.${pkg_dot};

import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.Getter;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.benchmark.util.MapperFactory;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.${pkg_dot}.${class_name};

import java.nio.ByteBuffer;
import java.time.OffsetDateTime;

public class ${class_name}BenchmarkSubject implements KmipBenchmarkSubject {

    private JsonMapper json;
    private XmlMapper xml;
    private TtlvMapper ttlv;

    private ${class_name} obj;

    @Getter private String jsonStr;
    @Getter private String xmlStr;
    @Getter private ByteBuffer ttlvBuf;

    public ${class_name}BenchmarkSubject() throws Exception { this.setup(); }

    @Override
    public String name() { return "${class_name}"; }

    @Override
    public void setup() throws Exception {
        json = MapperFactory.getJsonMapper();
        xml = MapperFactory.getXmlMapper();
        ttlv = MapperFactory.getTtlvMapper();

        obj = ${class_name}.builder()
            .activationDate(ActivationDateAttribute.builder().dateTime(OffsetDateTime.now()).build())
            .build();

        jsonStr = json.writeValueAsString(obj);
        xmlStr = xml.writeValueAsString(obj);
        ttlvBuf = ttlv.writeValueAsByteBuffer(obj);
    }

    @Override public void tearDown() { KmipContext.clear(); }
    @Override public String jsonSerialize() throws Exception { return json.writeValueAsString(obj); }
    @Override public Object jsonDeserialize() throws Exception { return json.readValue(jsonStr, ${class_name}.class); }
    @Override public String xmlSerialize() throws Exception { return xml.writeValueAsString(obj); }
    @Override public Object xmlDeserialize() throws Exception { return xml.readValue(xmlStr, ${class_name}.class); }
    @Override public ByteBuffer ttlvSerialize() throws Exception { return ttlv.writeValueAsByteBuffer(obj); }
    @Override public Object ttlvDeserialize() throws Exception { return ttlv.readValue(ttlvBuf.duplicate(), ${class_name}.class); }
}
EOF

    echo "Created: ${out_file}"
}

generate_serialization_test() {
    local class_name="$1"
    local package_path="$2"
    local format="$3"   # json|xml|ttlv
    local out_dir="${TEST_JAVA}/codec/${format}/${package_path}"
    local format_pascal
    format_pascal=$(get_pascal_case "${format}")
    local suite_name="${class_name}${format_pascal}Test"
    local base_suite="Abstract${format_pascal}SerializationSuite"
    local pkg_dot
    pkg_dot=$(slash_to_dot "${package_path}")

    if [ "${DRY_RUN}" = "true" ]; then
        echo "DRY RUN: would create ${format} serialization test for ${class_name}: ${out_dir}/${suite_name}.java"
        return 0
    fi

    mkdir -p "${out_dir}"

    cat > "${out_dir}/${suite_name}.java" <<EOF
package org.purpleBean.kmip.codec.${format}.${pkg_dot};

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.${pkg_dot}.${class_name};
import org.purpleBean.kmip.test.suite.${base_suite};
import java.time.OffsetDateTime;

@DisplayName("${class_name} ${format_pascal} Serialization Tests")
class ${suite_name} extends ${base_suite}<${class_name}> {

    @Override
    protected Class<${class_name}> type() { return ${class_name}.class; }

    @Override
    protected ${class_name} createDefault() {
        return ${class_name}.builder()
            .activationDate(ActivationDateAttribute.builder().dateTime(OffsetDateTime.now()).build())
            .build();
    }

    @Override
    protected ${class_name} createVariant() {
        return ${class_name}.builder()
            .activationDate(ActivationDateAttribute.builder().dateTime(OffsetDateTime.now()).build())
            .build();
    }
}
EOF

    echo "Created: ${out_dir}/${suite_name}.java"
}

#############################################
# Orchestrator for one structure
#############################################
generate_attribute_structure() {
    local STRUCTURE_NAME="$1"
    local package_path="$2"   # e.g. common/structure
    local struct_var
    struct_var=$(get_camel_case "${STRUCTURE_NAME}")
    local struct_snake
    struct_snake=$(to_snake_upper "${STRUCTURE_NAME}")
    local pkg_dot
    pkg_dot=$(slash_to_dot "${package_path}")

    printf "\nProcessing %s ...\n" "${STRUCTURE_NAME}"

    ${GEN_DOMAIN} && generate_domain_class "${STRUCTURE_NAME}" "${package_path}"
    ${GEN_TEST} && generate_domain_test "${STRUCTURE_NAME}" "${package_path}"
    ${GEN_JSON_SER} && generate_json_serializer "${STRUCTURE_NAME}" "${package_path}"
    ${GEN_JSON_DES} && generate_json_deserializer "${STRUCTURE_NAME}" "${package_path}"
    ${GEN_XML_SER} && generate_xml_serializer "${STRUCTURE_NAME}" "${package_path}"
    ${GEN_XML_DES} && generate_xml_deserializer "${STRUCTURE_NAME}" "${package_path}"
    ${GEN_TTLV_SER} && generate_ttlv_serializer "${STRUCTURE_NAME}" "${package_path}"
    ${GEN_TTLV_DES} && generate_ttlv_deserializer "${STRUCTURE_NAME}" "${package_path}"
    ${GEN_SERIAL_TEST} && generate_serialization_test "${STRUCTURE_NAME}" "${package_path}" "json" \
                  && generate_serialization_test "${STRUCTURE_NAME}" "${package_path}" "xml" \
                  && generate_serialization_test "${STRUCTURE_NAME}" "${package_path}" "ttlv"
    ${GEN_BENCHMARK} && generate_benchmark_subject "${STRUCTURE_NAME}" "${package_path}"
    ${GEN_SERVICES} && register_services "${STRUCTURE_NAME}" "${pkg_dot}"

    printf "Finished (or planned) generation for %s\n" "${STRUCTURE_NAME}"
    printf "Suggested enum entry to add to KmipTag.Standard:\n"
    printf "    %s(0x%s, \"%s\");\n" "${struct_snake}" "$(printf '%x' $(( (RANDOM % 65000) + 1000 )))" "${STRUCTURE_NAME}"
}

#############################################
# Main
#############################################
# Parse flags and arguments
if [ $# -eq 0 ]; then
    usage
fi

ATTRS=()
any_flag=false

while [ $# -gt 0 ]; do
    case "$1" in
        --domain) GEN_DOMAIN=true; any_flag=true; shift ;;
        --test) GEN_TEST=true; any_flag=true; shift ;;
        --json-ser) GEN_JSON_SER=true; any_flag=true; shift ;;
        --json-des) GEN_JSON_DES=true; any_flag=true; shift ;;
        --xml-ser) GEN_XML_SER=true; any_flag=true; shift ;;
        --xml-des) GEN_XML_DES=true; any_flag=true; shift ;;
        --ttlv-ser) GEN_TTLV_SER=true; any_flag=true; shift ;;
        --ttlv-des) GEN_TTLV_DES=true; any_flag=true; shift ;;
        --serial-test) GEN_SERIAL_TEST=true; any_flag=true; shift ;;
        --benchmark) GEN_BENCHMARK=true; any_flag=true; shift ;;
        --services) GEN_SERVICES=true; any_flag=true; shift ;;
        --all)
            GEN_DOMAIN=true; GEN_TEST=true; GEN_JSON_SER=true; GEN_JSON_DES=true;
            GEN_XML_SER=true; GEN_XML_DES=true; GEN_TTLV_SER=true; GEN_TTLV_DES=true;
            GEN_SERIAL_TEST=true; GEN_BENCHMARK=true; GEN_SERVICES=true;
            any_flag=true; shift ;;
        -h|--help) usage ;;
        --*) echo "Unknown option: $1"; usage ;;
        *) ATTRS[${#ATTRS[@]}]="$1"; shift ;;
    esac
done

if [ ${#ATTRS[@]} -eq 0 ]; then
    echo "Error: at least one structure name required."
    usage
fi

# If no generation flags provided -> dry run (show everything but don't write)
if [ "${any_flag}" = "false" ]; then
    DRY_RUN=true
    echo "No generation flags provided -> performing DRY RUN (no files written)."
    # Plan to show everything in dry run
    GEN_DOMAIN=true; GEN_TEST=true; GEN_JSON_SER=true; GEN_JSON_DES=true
    GEN_XML_SER=true; GEN_XML_DES=true; GEN_TTLV_SER=true; GEN_TTLV_DES=true
    GEN_SERIAL_TEST=true; GEN_BENCHMARK=true; GEN_SERVICES=true
fi

# Create directories once (prints in DRY_RUN)
create_directories "${MAIN_JAVA}" "${TEST_JAVA}" "${SUB_PATH}"

# Iterate structures
for s in "${ATTRS[@]}"; do
    # If passed name doesn't end with "Attribute" append it
    case "${s}" in
        *Attribute) name="${s}" ;;
        *) name="${s}Attribute" ;;
    esac
    generate_attribute_structure "${name}" "${SUB_PATH}"
done

if [ "${DRY_RUN}" = "true" ]; then
    echo ""
    echo "DRY RUN complete. Nothing was written. Re-run with flags (e.g. --all) to create files."
else
    echo ""
    echo "Generation complete. Don't forget to:"
    echo "  1. Add the suggested KmipTag.Standard entries"
    echo "  2. Fill in TODOs in generated code"
    echo "  3. Run your tests"
fi

exit 0

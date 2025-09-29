#!/bin/bash
# generate_datatype.sh
# Refactored generator for KMIP dataTypes.
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

DRY_RUN=false

#############################################
# Usage / Helpers
#############################################
usage() {
    cat <<EOF
Usage: $0 [options] <Name1> [Name2 ...]

If no generation options are provided the script performs a DRY RUN (prints what it would do).
To actually create files pass one or more generation flags.

Options:
  --class         Generate the  class
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

generate_data_class() {
    local DATA_NAME="$1"
    local DATA_NAME_SNAKE
    DATA_NAME_SNAKE="$(get_snake_case "${DATA_NAME}")"
    local out_dir="${MAIN_JAVA}/${SUB_PATH}"
    local out_file="${out_dir}/${DATA_NAME}.java"
    local pdot
    pdot="$(pkg_dot)"

    if [ "${DRY_RUN}" = "true" ]; then
        echo "DRY RUN: would create dataType class: ${out_file}"
        return 0
    fi

    mkdir -p "${out_dir}"

    cat > "${out_file}" <<EOF
package org.purpleBean.kmip.${pdot};

import lombok.*;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.common.*;
import org.purpleBean.kmip.common.enumeration.*;
import org.purpleBean.kmip.KmipDataType;

import java.time.OffsetDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * KMIP ${DATA_NAME} dataType.
 */
@Data
@Builder
public class ${DATA_NAME} implements KmipDataType {

    public static final KmipTag kmipTag = new KmipTag(KmipTag.Standard.${DATA_NAME_SNAKE});
    public static final EncodingType encodingType = EncodingType.DATE_TIME;
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2);

    static {
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, ${DATA_NAME}.class);
        }
    }
    
        
    // TODO: Add your dataType field here with required java type and set the var name to value
    // Example:
    @NonNull
    private final OffsetDateTime value;
    
    public static ${DATA_NAME} of(@NonNull OffsetDateTime value) {
        return ${DATA_NAME}.builder().value(value).build();
    }

    @Override
    public KmipTag getKmipTag() {
        return kmipTag;
    }

    @Override
    public EncodingType getEncodingType() {
        return encodingType;
    }

    @Override
    public boolean isSupported() {
        KmipSpec spec = KmipContext.getSpec();
        return supportedVersions.contains(spec);
    }
}
EOF

    echo "Created: ${out_file}"
}

generate_json_serializer() {
    local DATA_NAME="$1"
    local out_dir="${MAIN_JAVA}/codec/json/serializer/kmip/${SUB_PATH}"
    local out_file="${out_dir}/${DATA_NAME}JsonSerializer.java"
    local pdot varname
    pdot="$(pkg_dot)"
    varname="$(get_var_name "${DATA_NAME}")"

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
import org.purpleBean.kmip.${pdot}.${DATA_NAME};

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.time.OffsetDateTime;
import java.util.List;

public class ${DATA_NAME}JsonSerializer extends KmipDataTypeJsonSerializer<${DATA_NAME}> {

    @Override
    public void serialize(${DATA_NAME} ${varname}, JsonGenerator gen, SerializerProvider serializerProvider) throws IOException {
        // Validation: Null check
        if (${varname} == null) {
            return;
        }

        // Validation: KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();
        if (!${varname}.isSupported()) {
            throw new UnsupportedEncodingException(
                    String.format("%s is not supported for KMIP spec %s", ${varname}.getKmipTag().getDescription(), spec)
            );
        }

        gen.writeStartObject();
        gen.writeObject(${varname}.getKmipTag());
        gen.writeStringField("type", ${varname}.getEncodingType().getDescription());
        gen.writeObjectField("value", ${varname}.getValue());
        gen.writeEndObject();
    }
}
EOF

    add_service_entry "src/main/resources/META-INF/services/org.purpleBean.kmip.codec.json.serializer.kmip.KmipDataTypeJsonSerializer" \
        "org.purpleBean.kmip.codec.json.serializer.kmip.${pdot}.${DATA_NAME}JsonSerializer"

    echo "Created: ${out_file}"
}

generate_json_deserializer() {
    local DATA_NAME="$1"
    local out_dir="${MAIN_JAVA}/codec/json/deserializer/kmip/${SUB_PATH}"
    local out_file="${out_dir}/${DATA_NAME}JsonDeserializer.java"
    local pdot varname data_snake
    pdot="$(pkg_dot)"
    data_snake="$(get_snake_case "${DATA_NAME}")"
    varname="$(get_var_name "${DATA_NAME}")"

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
import org.purpleBean.kmip.${pdot}.${DATA_NAME};

import java.io.IOException;
import java.time.OffsetDateTime;
import java.util.NoSuchElementException;

public class ${DATA_NAME}JsonDeserializer extends KmipDataTypeJsonDeserializer<${DATA_NAME}> {
    private final KmipTag kmipTag = ${DATA_NAME}.kmipTag;
    private final EncodingType encodingType = ${DATA_NAME}.encodingType;

    @Override
    public ${DATA_NAME} deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();

        if (node == null) {
            ctxt.reportInputMismatch(${DATA_NAME}.class, String.format("JSON node cannot be null for ${DATA_NAME} deserialization"));
            return null;
        }

        // Validation: Extract and validate KMIP tag
        KmipTag tag;
        try {
            tag = p.getCodec().treeToValue(node, KmipTag.class);
            if (tag == null) {
                ctxt.reportInputMismatch(${DATA_NAME}.class, String.format("Invalid KMIP tag for ${DATA_NAME}"));
                return null;
            }
        } catch (Exception e) {
            ctxt.reportInputMismatch(${DATA_NAME}.class, String.format("Failed to parse KMIP tag for ${DATA_NAME}: %s", e.getMessage()));
            return null;
        }

        if (!node.isObject() || tag.getValue().getValue() != kmipTag.getValue().getValue()) {
            ctxt.reportInputMismatch(${DATA_NAME}.class,
                    String.format("Expected object with %s tag for ${DATA_NAME}, got tag: %s", kmipTag.getValue().getValue(), tag.getValue().getValue()));
            return null;
        }

        // Validation: Extract and validate type field
        JsonNode typeNode = node.get("type");
        if (typeNode == null
                || !typeNode.isTextual()
                || EncodingType.fromName(typeNode.asText()).isEmpty()
                || EncodingType.fromName(typeNode.asText()).get() != encodingType
        ) {
            ctxt.reportInputMismatch(${DATA_NAME}.class, String.format("Missing or non-text 'type' field for ${DATA_NAME}"));
            return null;
        }

        // Validation: Extract and validate value field
        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(${DATA_NAME}.class, "${DATA_NAME} 'value' must be a non-empty array");
            return null;
        }

        // TODO: update with required java type
        OffsetDateTime dateTime = p.getCodec().treeToValue(valueNode, OffsetDateTime.class);
        ${DATA_NAME} ${varname} = ${DATA_NAME}.builder().value(dateTime).build();

        // Validate KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();

        if (!${varname}.isSupported()) {
            ctxt.reportInputMismatch(${DATA_NAME}.class, "${DATA_NAME} not supported for spec " + spec);
            return null;
        }

        return ${varname};
    }
}
EOF

    add_service_entry "src/main/resources/META-INF/services/org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer" \
        "org.purpleBean.kmip.codec.json.deserializer.kmip.${pdot}.${DATA_NAME}JsonDeserializer"

    echo "Created: ${out_file}"
}

generate_xml_serializer() {
    local DATA_NAME="$1"
    local out_dir="${MAIN_JAVA}/codec/xml/serializer/kmip/${SUB_PATH}"
    local out_file="${out_dir}/${DATA_NAME}XmlSerializer.java"
    local pdot varname
    pdot="$(pkg_dot)"
    varname="$(get_var_name "${DATA_NAME}")"

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
import org.purpleBean.kmip.codec.xml.serializer.kmip.KmipDataTypeXmlSerializer;
import org.purpleBean.kmip.${pdot}.${DATA_NAME};

import javax.xml.namespace.QName;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.time.OffsetDateTime;
import java.util.List;

public class ${DATA_NAME}XmlSerializer extends KmipDataTypeXmlSerializer<${DATA_NAME}> {

    @Override
    public void serialize(${DATA_NAME} ${varname}, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        // Validation: KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();
        if (!${varname}.isSupported()) {
            throw new UnsupportedEncodingException(String.format("%s not supported for KMIP spec %s", ${varname}.getClass().getSimpleName(), spec));
        }

        if (!(gen instanceof ToXmlGenerator xmlGen)) {
            throw new IllegalStateException("Expected ToXmlGenerator");
        }

        // Start element with name from kmipTag
        String elementName = ${varname}.getKmipTag().getDescription();
        xmlGen.setNextName(QName.valueOf(elementName));
        xmlGen.writeStartObject(${varname});

        xmlGen.setNextIsAttribute(true);
        xmlGen.writeStringField("type", ${varname}.getEncodingType().getDescription());
        xmlGen.setNextIsAttribute(true);
        xmlGen.writeObjectField("value", ${varname}.getValue());
        xmlGen.writeEndObject();
    }
}
EOF

    add_service_entry "src/main/resources/META-INF/services/org.purpleBean.kmip.codec.xml.serializer.kmip.KmipDataTypeXmlSerializer" \
        "org.purpleBean.kmip.codec.xml.serializer.kmip.${pdot}.${DATA_NAME}XmlSerializer"

    echo "Created: ${out_file}"
}

generate_xml_deserializer() {
    local DATA_NAME="$1"
    local out_dir="${MAIN_JAVA}/codec/xml/deserializer/kmip/${SUB_PATH}"
    local out_file="${out_dir}/${DATA_NAME}XmlDeserializer.java"
    local pdot varname data_snake
    pdot="$(pkg_dot)"
    varname="$(get_var_name "${DATA_NAME}")"
    data_snake="$(get_snake_case "${DATA_NAME}")"

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
import org.purpleBean.kmip.codec.xml.deserializer.kmip.KmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.${pdot}.${DATA_NAME};

import java.io.IOException;
import java.time.OffsetDateTime;
import java.util.Map;

public class ${DATA_NAME}XmlDeserializer extends KmipDataTypeXmlDeserializer<${DATA_NAME}> {
    private final KmipTag kmipTag = ${DATA_NAME}.kmipTag;
    private final EncodingType encodingType = ${DATA_NAME}.encodingType;

    @Override
    public ${DATA_NAME} deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(${DATA_NAME}.class, "Expected XML object for ${DATA_NAME}");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
              && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(${DATA_NAME}.class, "Invalid Tag for ${DATA_NAME}");
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() ||
                !encodingType.getDescription().equals(typeNode.asText())) {
            ctxt.reportInputMismatch(${DATA_NAME}.class, "Missing or invalid '@type' attribute for ${DATA_NAME}");
            return null;
        }

        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(${DATA_NAME}.class,
                "Missing or non-text 'value' for ${DATA_NAME}");
            return null;
        }

        OffsetDateTime dateTime = OffsetDateTime.parse(valueNode.asText());
        ${DATA_NAME} ${varname} = ${DATA_NAME}.builder().value(dateTime).build();

        KmipSpec spec = KmipContext.getSpec();

        if (!${varname}.isSupported()) {
            ctxt.reportInputMismatch(${DATA_NAME}.class, "${DATA_NAME} not supported for spec " + spec);
            return null;
        }

        return ${varname};
    }
}
EOF

    add_service_entry "src/main/resources/META-INF/services/org.purpleBean.kmip.codec.xml.deserializer.kmip.KmipDataTypeXmlDeserializer" \
        "org.purpleBean.kmip.codec.xml.deserializer.kmip.${pdot}.${DATA_NAME}XmlDeserializer"

    echo "Created: ${out_file}"
}

generate_ttlv_serializer() {
    local DATA_NAME="$1"
    local out_dir="${MAIN_JAVA}/codec/ttlv/serializer/kmip/${SUB_PATH}"
    local out_file="${out_dir}/${DATA_NAME}TtlvSerializer.java"
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
import org.purpleBean.kmip.${pdot}.${DATA_NAME};

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

public class ${DATA_NAME}TtlvSerializer extends KmipDataTypeTtlvSerializer<${DATA_NAME}> {
    @Override
    public ByteBuffer serialize(${DATA_NAME} value, TtlvMapper mapper) throws IOException {
        return serializeToTtlvObject(value, mapper).toByteBuffer();
    }

    private TtlvObject serializeToTtlvObject(${DATA_NAME} value, TtlvMapper mapper) throws IOException {
        if (value == null) {
            return null;
        }

        KmipSpec spec = KmipContext.getSpec();
        if (!value.isSupported()) {
            throw new IOException(
                String.format("%s is not supported for KMIP spec %s",
                        value.getKmipTag().getDescription(), spec)
            );
        }

        byte[] tag = value.getKmipTag().getTagBytes();
        byte type = value.getEncodingType().getTypeValue();
        byte[] payload = mapper.writeValueAsByteBuffer(value.getValue()).array();

        return TtlvObject.builder()
                .tag(tag)
                .type(type)
                .value(payload)
                .build();
    }
}
EOF

    add_service_entry "src/main/resources/META-INF/services/org.purpleBean.kmip.codec.ttlv.serializer.kmip.KmipDataTypeTtlvSerializer" \
        "org.purpleBean.kmip.codec.ttlv.serializer.kmip.${pdot}.${DATA_NAME}TtlvSerializer"

    echo "Created: ${out_file}"
}

generate_ttlv_deserializer() {
    local DATA_NAME="$1"
    local out_dir="${MAIN_JAVA}/codec/ttlv/deserializer/kmip/${SUB_PATH}"
    local out_file="${out_dir}/${DATA_NAME}TtlvDeserializer.java"
    local pdot varname data_snake
    pdot="$(pkg_dot)"
    varname="$(get_var_name "${DATA_NAME}")"
    data_snake="$(get_snake_case "${DATA_NAME}")"

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
import org.purpleBean.kmip.codec.ttlv.TtlvObject;
import org.purpleBean.kmip.codec.ttlv.TtlvConstants;
import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.KmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.${pdot}.${DATA_NAME};

import java.io.IOException;
import java.nio.ByteBuffer;
import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

public class ${DATA_NAME}TtlvDeserializer extends KmipDataTypeTtlvDeserializer<${DATA_NAME}> {
    private final KmipTag kmipTag = ${DATA_NAME}.kmipTag;
    private final EncodingType encodingType = ${DATA_NAME}.encodingType;

    @Override
    public ${DATA_NAME} deserialize(ByteBuffer ttlvBuffer, TtlvMapper mapper) throws IOException {
        TtlvObject obj = TtlvObject.fromBuffer(ttlvBuffer);
        if (Arrays.equals(obj.getTag(), kmipTag.getTagBytes()) && obj.getType() != encodingType.getTypeValue()) {
            throw new IllegalArgumentException(String.format("Expected %s type for %s, got %s", encodingType.getTypeValue(), kmipTag.getDescription(), obj.getType()));
        }

        ByteBuffer bb = ByteBuffer.wrap(obj.getValue()).order(TtlvConstants.BYTE_ORDER);
        // TODO : update with required java type
        OffsetDateTime dt = mapper.readValue(bb, OffsetDateTime.class);
        ${DATA_NAME} ${varname} = ${DATA_NAME}.builder().value(dt).build();

        KmipSpec spec = KmipContext.getSpec();

        if (!${varname}.isSupported()) {
            throw new NoSuchElementException(String.format("${DATA_NAME} not supported for spec %s", spec));
        }
        return ${varname};
    }
}
EOF

    add_service_entry "src/main/resources/META-INF/services/org.purpleBean.kmip.codec.ttlv.deserializer.kmip.KmipDataTypeTtlvDeserializer" \
        "org.purpleBean.kmip.codec.ttlv.deserializer.kmip.${pdot}.${DATA_NAME}TtlvDeserializer"

    echo "Created: ${out_file}"
}

generate_domain_test() {
    local DATA_NAME="$1"
    local out_dir="${TEST_JAVA}/${SUB_PATH}"
    local out_file="${out_dir}/${DATA_NAME}Test.java"
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
import org.purpleBean.kmip.common.enumeration.State;
import org.purpleBean.kmip.test.suite.AbstractKmipDataTypeSuite;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;

@DisplayName("${DATA_NAME} Domain Tests")
class ${DATA_NAME}Test extends AbstractKmipDataTypeSuite<${DATA_NAME}> {

    @Override
    protected Class<${DATA_NAME}> type() {
        return ${DATA_NAME}.class;
    }

    @Override
    protected ${DATA_NAME} createDefault() {
        // TODO: Update with actual default values for your dataType
        OffsetDateTime FIXED_TIME = OffsetDateTime.of(2024, 1, 2, 3, 4, 5, 0, ZoneOffset.UTC);
        return ${DATA_NAME}.builder().value(FIXED_TIME).build();
    }

    @Override
    protected EncodingType expectedEncodingType() {
        // TODO: Update with actual encoding type for your dataType
        return EncodingType.DATE_TIME;
    }
}
EOF

    echo "Created: ${out_file}"
}

generate_serialization_test_for_format() {
    local DATA_NAME="$1"
    local format="$2"
    local format_upper
    format_upper="$(get_upper_case "${format}")"
    local format_pascal
    format_pascal="$(get_pascal_case "${format}")"
    local suite_name="${DATA_NAME}${format_pascal}Test"
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
import org.purpleBean.kmip.${pdot}.${DATA_NAME};
import org.purpleBean.kmip.test.suite.Abstract${format_pascal}SerializationSuite;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.common.*;
import org.purpleBean.kmip.common.enumeration.*;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@DisplayName("${DATA_NAME} ${format_upper} Serialization Tests")
class ${suite_name} extends Abstract${format_pascal}SerializationSuite<${DATA_NAME}> {

    private static final OffsetDateTime FIXED_TIME = OffsetDateTime.of(2024, 1, 2, 3, 4, 5, 0, ZoneOffset.UTC);

    @Override
    protected Class<${DATA_NAME}> type() {
        return ${DATA_NAME}.class;
    }

    @Override
    protected ${DATA_NAME} createDefault() {
        // TODO: Update with actual default values for your dataType
        return ${DATA_NAME}.builder().value(FIXED_TIME).build();
    }

    @Override
    protected ${DATA_NAME} createVariant() {
        // TODO: Update with different values to test variations
        return ${DATA_NAME}.builder().value(FIXED_TIME.plusDays(1)).build();
    }
}
EOF

    echo "Created: ${out_file}"
}

generate_benchmark_subject() {
    local DATA_NAME="$1"
    local out_dir="${TEST_JAVA}/benchmark/subjects/${SUB_PATH}"
    local out_file="${out_dir}/${DATA_NAME}BenchmarkSubject.java"
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
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.codec.KmipCodecManager;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.${pdot}.${DATA_NAME};

import java.nio.ByteBuffer;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

public class ${DATA_NAME}BenchmarkSubject extends KmipBenchmarkSubject {

    private JsonMapper json;
    private XmlMapper xml;
    private TtlvMapper ttlv;

    private ${DATA_NAME} obj;

    @Getter
    private String jsonStr;
    @Getter
    private String xmlStr;
    @Getter
    private ByteBuffer ttlvBuf;

    public ${DATA_NAME}BenchmarkSubject() throws Exception {
        this.setup();
    }

    @Override
    public String name() {
        return "${DATA_NAME}";
    }

    @Override
    public void setup() throws Exception {
        json = KmipCodecManager.getJsonMapper();
        xml = KmipCodecManager.getXmlMapper();
        ttlv = KmipCodecManager.getTtlvMapper();

        var fixed = OffsetDateTime.of(2024, 1, 2, 3, 4, 5, 0, ZoneOffset.UTC);
        obj = ${DATA_NAME}.builder().value(fixed).build();

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
        return json.readValue(jsonStr, ${DATA_NAME}.class);
    }

    @Override
    public String xmlSerialize() throws Exception {
        return xml.writeValueAsString(obj);
    }

    @Override
    public Object xmlDeserialize() throws Exception {
        return xml.readValue(xmlStr, ${DATA_NAME}.class);
    }

    @Override
    public ByteBuffer ttlvSerialize() throws Exception {
        return ttlv.writeValueAsByteBuffer(obj);
    }

    @Override
    public Object ttlvDeserialize() throws Exception {
        return ttlv.readValue(ttlvBuf.duplicate(), ${DATA_NAME}.class);
    }
}
EOF

    add_service_entry "src/test/resources/META-INF/services/org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject" \
        "org.purpleBean.kmip.benchmark.subjects.${pdot}.${DATA_NAME}BenchmarkSubject"

    echo "Created: ${out_file}"
}

#############################################
# Orchestrator for a single dataType
#############################################
generate_datatype() {
    local DATA_NAME="$1"
    echo
    echo "Processing dataType: ${DATA_NAME}"

    ${GEN_CLASS} && generate_data_class "${DATA_NAME}"
    ${GEN_JSON_SER} && generate_json_serializer "${DATA_NAME}"
    ${GEN_JSON_DES} && generate_json_deserializer "${DATA_NAME}"
    ${GEN_XML_SER} && generate_xml_serializer "${DATA_NAME}"
    ${GEN_XML_DES} && generate_xml_deserializer "${DATA_NAME}"
    ${GEN_TTLV_SER} && generate_ttlv_serializer "${DATA_NAME}"
    ${GEN_TTLV_DES} && generate_ttlv_deserializer "${DATA_NAME}"
    ${GEN_DOMAIN_TEST} && generate_domain_test "${DATA_NAME}"
    ${GEN_JSON_TEST} && generate_serialization_test_for_format "${DATA_NAME}" "json"
    ${GEN_XML_TEST} && generate_serialization_test_for_format "${DATA_NAME}" "xml"
    ${GEN_TTLV_TEST} && generate_serialization_test_for_format "${DATA_NAME}" "ttlv"
    ${GEN_BENCHMARK} && generate_benchmark_subject "${DATA_NAME}"

    echo "Finished (or planned) generation for ${DATA_NAME}"
    echo "Remember to fill in TODOs and validate generated files."
}

#############################################
# Main
#############################################
if [ $# -eq 0 ]; then
    usage
fi

DATATYPES=()
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
        *) DATATYPES[${#DATATYPES[@]}]="$1"; shift ;;
    esac
done

if [ ${#DATATYPES[@]} -eq 0 ]; then
    echo "Error: at least one dataType name required."
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

# Process each dataType
i=0
while [ "${i}" -lt "${#DATATYPES[@]}" ]; do
    data_name="${DATATYPES[$i]}"
    generate_datatype "${data_name}"
    i=$((i + 1))
done

if [ "${DRY_RUN}" = "true" ]; then
    echo
    echo "DRY RUN complete. Nothing was written."
    echo "Re-run with flags (e.g. --all) to actually generate files."
else
    echo
    echo "Generation complete for ${#DATATYPES[@]} dataType(s)."
    echo "Don't forget to fill in TODOs and review generated code."
fi

exit 0

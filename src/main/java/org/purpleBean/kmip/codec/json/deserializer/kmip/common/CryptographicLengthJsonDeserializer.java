package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.CryptographicLength;

import java.io.IOException;
import java.util.NoSuchElementException;

public class CryptographicLengthJsonDeserializer extends KmipDataTypeJsonDeserializer<CryptographicLength> {
    private final KmipTag kmipTag = CryptographicLength.kmipTag;
    private final EncodingType encodingType = CryptographicLength.encodingType;

    @Override
    public CryptographicLength deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();

        if (node == null) {
            ctxt.reportInputMismatch(CryptographicLength.class, String.format("JSON node cannot be null for CryptographicLength deserialization"));
            return null;
        }

        // Validation: Extract and validate KMIP tag
        KmipTag tag;
        try {
            tag = p.getCodec().treeToValue(node, KmipTag.class);
            if (tag == null) {
                ctxt.reportInputMismatch(CryptographicLength.class, String.format("Invalid KMIP tag for CryptographicLength"));
                return null;
            }
        } catch (Exception e) {
            ctxt.reportInputMismatch(CryptographicLength.class, String.format("Failed to parse KMIP tag for CryptographicLength: %s", e.getMessage()));
            return null;
        }

        if (!node.isObject() || tag.getValue().getValue() != kmipTag.getValue().getValue()) {
            ctxt.reportInputMismatch(CryptographicLength.class,
                    String.format("Expected object with %s tag for CryptographicLength, got tag: %s", kmipTag.getValue().getValue(), tag.getValue().getValue()));
            return null;
        }

        // Validation: Extract and validate type field
        JsonNode typeNode = node.get("type");
        if (typeNode == null
                || !typeNode.isTextual()
                || EncodingType.fromName(typeNode.asText()).isEmpty()
                || EncodingType.fromName(typeNode.asText()).get() != encodingType
        ) {
            ctxt.reportInputMismatch(CryptographicLength.class, String.format("Missing or non-text 'type' field for CryptographicLength"));
            return null;
        }

        // Validation: Extract and validate value field
        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isNumber()) {
            ctxt.reportInputMismatch(CryptographicLength.class, "CryptographicLength 'value' must be a non-empty number value");
            return null;
        }

        // Parse the integer value for cryptographic length (in bits)
        Integer length = valueNode.asInt();
        if (length < 0) {
            ctxt.reportInputMismatch(CryptographicLength.class, "CryptographicLength value must be a non-negative integer");
            return null;
        }
        CryptographicLength cryptographicLength = CryptographicLength.of(length);

        // Validate KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();
        if (!cryptographicLength.isSupportedFor(spec)) {
            throw new NoSuchElementException(String.format("CryptographicLength is not supported for KMIP spec %s", spec));
        }

        return cryptographicLength;
    }
}

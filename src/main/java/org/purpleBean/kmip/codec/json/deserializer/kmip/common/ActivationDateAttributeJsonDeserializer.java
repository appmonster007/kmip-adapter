package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.ActivationDateAttribute;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.util.NoSuchElementException;

/**
 * JSON deserializer for ActivationDate.
 */
public class ActivationDateAttributeJsonDeserializer extends KmipDataTypeJsonDeserializer<ActivationDateAttribute> {
    private final KmipTag kmipTag = new KmipTag(KmipTag.Standard.ACTIVATION_DATE);
    private final EncodingType encodingType = EncodingType.DATE_TIME; // TODO : update the encoding type

    @Override
    public ActivationDateAttribute deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();

        if (node == null) {
            ctxt.reportInputMismatch(ActivationDateAttribute.class, "JSON node cannot be null for ActivationDateAttribute deserialization");
            return null;
        }

        // Validation: Extract and validate KMIP tag
        KmipTag tag;
        try {
            tag = p.getCodec().treeToValue(node, KmipTag.class);
            if (tag == null) {
                ctxt.reportInputMismatch(ActivationDateAttribute.class, "Invalid KMIP tag for ActivationDateAttribute");
                return null;
            }
        } catch (Exception e) {
            ctxt.reportInputMismatch(ActivationDateAttribute.class, String.format("Failed to parse KMIP tag for ActivationDateAttribute: %s", e.getMessage()));
            return null;
        }

        if (!node.isObject() || tag.getValue().getValue() != kmipTag.getValue().getValue()) {
            ctxt.reportInputMismatch(ActivationDateAttribute.class, "Expected object for ActivationDateAttribute");
            return null;
        }

        // Validation: Extract and validate type field
        JsonNode typeNode = node.get("type");
        if (typeNode == null
                || !typeNode.isTextual()
                || EncodingType.fromName(typeNode.asText()).isEmpty()
                || EncodingType.fromName(typeNode.asText()).get() != encodingType
        ) {
            ctxt.reportInputMismatch(ActivationDateAttribute.class, "Missing or non-text 'type' field for ActivationDateAttribute");
            return null;
        }

        // Validation: Extract and validate value field
        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(ActivationDateAttribute.class, "Missing or non-text 'value' for ActivationDateAttribute");
            return null;
        }

        OffsetDateTime dateTime = OffsetDateTime.parse(valueNode.asText());
        ActivationDateAttribute attribute = ActivationDateAttribute.builder().dateTime(dateTime).build();

        KmipSpec spec = KmipContext.getSpec();
        if (!attribute.isSupportedFor(spec)) {
            throw new NoSuchElementException(
                    String.format("ActivationDateAttribute '%s' is not supported for KMIP spec %s", valueNode.asText(), spec)
            );
        }
        return attribute;
    }
}

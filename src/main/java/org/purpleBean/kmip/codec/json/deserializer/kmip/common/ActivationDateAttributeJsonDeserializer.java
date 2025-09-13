package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.common.ActivationDateAttribute;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.util.NoSuchElementException;

public class ActivationDateAttributeJsonDeserializer extends KmipDataTypeJsonDeserializer<ActivationDateAttribute> {

    @Override
    public ActivationDateAttribute deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();

        KmipTag.Value tag = p.getCodec().treeToValue(node, KmipTag.class).getValue();

        if (!node.isObject() || tag != KmipTag.Standard.ACTIVATION_DATE) {
            ctxt.reportInputMismatch(ActivationDateAttribute.class, "Expected object for ActivationDateAttribute");
            return null;
        }

        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(ActivationDateAttribute.class, "Missing or non-text 'value' for ActivationDateAttribute");
            return null;
        }

        OffsetDateTime dateTime = OffsetDateTime.parse(valueNode.asText());
        ActivationDateAttribute activationDateAttribute = ActivationDateAttribute.builder().dateTime(dateTime).build();

        KmipSpec spec = KmipContext.getSpec();

        if (!activationDateAttribute.isSupportedFor(spec)) {
            throw new NoSuchElementException();
        }

        return activationDateAttribute;
    }
}



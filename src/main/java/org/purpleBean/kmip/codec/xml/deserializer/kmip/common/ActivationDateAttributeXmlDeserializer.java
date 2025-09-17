package org.purpleBean.kmip.codec.xml.deserializer.kmip.common;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.codec.xml.deserializer.kmip.KmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.common.ActivationDateAttribute;
import org.purpleBean.kmip.common.enumeration.State;

import java.io.IOException;
import java.time.OffsetDateTime;

public class ActivationDateAttributeXmlDeserializer extends KmipDataTypeXmlDeserializer<ActivationDateAttribute> {

    @Override
    public ActivationDateAttribute deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();
        if (!node.isObject()) {
            ctxt.reportInputMismatch(ActivationDateAttribute.class, "Expected object for ActivationDateAttribute");
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() ||
                !EncodingType.DATE_TIME.getDescription().equals(typeNode.asText())) {
            ctxt.reportInputMismatch(State.class, "Missing or invalid '@type' attribute for ActivationDateAttribute");
            return null;
        }


        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(ActivationDateAttribute.class, "Missing or non-text 'value' for ActivationDateAttribute");
            return null;
        }
        return ActivationDateAttribute.builder().dateTime(OffsetDateTime.parse(valueNode.asText())).build();
    }
}



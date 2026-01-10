package org.purpleBean.kmip.codec.xml.deserializer.kmip.common;

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
import org.purpleBean.kmip.common.AttributeValueBoolean;

import java.io.IOException;

public class AttributeValueBooleanXmlDeserializer extends KmipDataTypeXmlDeserializer<AttributeValueBoolean> {
    private final KmipTag kmipTag = AttributeValueBoolean.kmipTag;
    private final EncodingType encodingType = AttributeValueBoolean.encodingType;

    @Override
    public AttributeValueBoolean deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(AttributeValueBoolean.class, "Expected XML object for AttributeValue.Boolean");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
                && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(AttributeValueBoolean.class, "Invalid Tag for AttributeValue.Boolean");
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() ||
                !encodingType.getDescription().equals(typeNode.asText())) {
            ctxt.reportInputMismatch(AttributeValueBoolean.class, "Missing or invalid '@type' attribute for AttributeValue.Boolean");
            return null;
        }

        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(AttributeValueBoolean.class,
                    "Missing or non-boolean 'value' for AttributeValue.Boolean");
            return null;
        }

        java.lang.Boolean value = codec.treeToValue(valueNode, java.lang.Boolean.class);
        AttributeValueBoolean attributeValueBoolean = AttributeValueBoolean.of(value);

        KmipSpec spec = KmipContext.getSpec();

        if (!attributeValueBoolean.isSupported()) {
            ctxt.reportInputMismatch(AttributeValueBoolean.class, "AttributeValue.Boolean not supported for spec " + spec);
            return null;
        }

        return attributeValueBoolean;
    }
}

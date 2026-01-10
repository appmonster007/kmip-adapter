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
import org.purpleBean.kmip.common.AttributeValueEnumeration;

import java.io.IOException;

public class AttributeValueEnumerationXmlDeserializer extends KmipDataTypeXmlDeserializer<AttributeValueEnumeration> {
    private final KmipTag kmipTag = AttributeValueEnumeration.kmipTag;
    private final EncodingType encodingType = AttributeValueEnumeration.encodingType;

    @Override
    public AttributeValueEnumeration deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(AttributeValueEnumeration.class, "Expected XML object for AttributeValue.Enumeration");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
                && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(AttributeValueEnumeration.class, "Invalid Tag for AttributeValue.Enumeration");
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() ||
                !encodingType.getDescription().equals(typeNode.asText())) {
            ctxt.reportInputMismatch(AttributeValueEnumeration.class, "Missing or invalid '@type' attribute for AttributeValue.Enumeration");
            return null;
        }

        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(AttributeValueEnumeration.class,
                    "Missing or non-number 'value' for AttributeValue.Enumeration");
            return null;
        }

        java.lang.Integer value = codec.treeToValue(valueNode, java.lang.Integer.class);
        AttributeValueEnumeration attributeValueEnumeration = AttributeValueEnumeration.of(value);

        KmipSpec spec = KmipContext.getSpec();

        if (!attributeValueEnumeration.isSupported()) {
            ctxt.reportInputMismatch(AttributeValueEnumeration.class, "AttributeValue.Enumeration not supported for spec " + spec);
            return null;
        }

        return attributeValueEnumeration;
    }
}

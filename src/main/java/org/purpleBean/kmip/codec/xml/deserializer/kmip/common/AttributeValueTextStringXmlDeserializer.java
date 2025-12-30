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
import org.purpleBean.kmip.common.AttributeValue;

import java.io.IOException;

public class AttributeValueTextStringXmlDeserializer extends KmipDataTypeXmlDeserializer<AttributeValue.TextString> {
    private final KmipTag kmipTag = AttributeValue.TextString.kmipTag;
    private final EncodingType encodingType = AttributeValue.TextString.encodingType;

    @Override
    public AttributeValue.TextString deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(AttributeValue.TextString.class, "Expected XML object for AttributeValue.TextString");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
                && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(AttributeValue.TextString.class, "Invalid Tag for AttributeValue.TextString");
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() ||
                !encodingType.getDescription().equals(typeNode.asText())) {
            ctxt.reportInputMismatch(AttributeValue.TextString.class, "Missing or invalid '@type' attribute for AttributeValue.TextString");
            return null;
        }

        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(AttributeValue.TextString.class,
                    "Missing or non-text 'value' for AttributeValue.TextString");
            return null;
        }

        String value = codec.treeToValue(valueNode, String.class);
        AttributeValue.TextString attributeValueTextString = AttributeValue.TextString.of(value);

        KmipSpec spec = KmipContext.getSpec();

        if (!attributeValueTextString.isSupported()) {
            ctxt.reportInputMismatch(AttributeValue.TextString.class, "AttributeValue.TextString not supported for spec " + spec);
            return null;
        }

        return attributeValueTextString;
    }
}

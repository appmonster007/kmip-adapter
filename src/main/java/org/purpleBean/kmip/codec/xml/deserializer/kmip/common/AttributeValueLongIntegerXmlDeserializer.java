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
import org.purpleBean.kmip.common.AttributeValueLongInteger;

import java.io.IOException;

public class AttributeValueLongIntegerXmlDeserializer extends KmipDataTypeXmlDeserializer<AttributeValueLongInteger> {
    private final KmipTag kmipTag = AttributeValueLongInteger.kmipTag;
    private final EncodingType encodingType = AttributeValueLongInteger.encodingType;

    @Override
    public AttributeValueLongInteger deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(AttributeValueLongInteger.class, "Expected XML object for AttributeValue.LongInteger");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
                && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(AttributeValueLongInteger.class, "Invalid Tag for AttributeValue.LongInteger");
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() ||
                !encodingType.getDescription().equals(typeNode.asText())) {
            ctxt.reportInputMismatch(AttributeValueLongInteger.class, "Missing or invalid '@type' attribute for AttributeValue.LongInteger");
            return null;
        }

        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(AttributeValueLongInteger.class,
                    "Missing or non-number 'value' for AttributeValue.LongInteger");
            return null;
        }

        java.lang.Long value = codec.treeToValue(valueNode, java.lang.Long.class);
        AttributeValueLongInteger attributeValueLongInteger = AttributeValueLongInteger.of(value);

        KmipSpec spec = KmipContext.getSpec();

        if (!attributeValueLongInteger.isSupported()) {
            ctxt.reportInputMismatch(AttributeValueLongInteger.class, "AttributeValue.LongInteger not supported for spec " + spec);
            return null;
        }

        return attributeValueLongInteger;
    }
}

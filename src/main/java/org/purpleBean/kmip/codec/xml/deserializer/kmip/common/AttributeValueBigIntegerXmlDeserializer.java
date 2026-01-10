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
import org.purpleBean.kmip.common.AttributeValueBigInteger;

import java.io.IOException;
import java.math.BigInteger;

public class AttributeValueBigIntegerXmlDeserializer extends KmipDataTypeXmlDeserializer<AttributeValueBigInteger> {
    private final KmipTag kmipTag = AttributeValueBigInteger.kmipTag;
    private final EncodingType encodingType = AttributeValueBigInteger.encodingType;

    @Override
    public AttributeValueBigInteger deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(AttributeValueBigInteger.class, "Expected XML object for AttributeValue.BigInteger");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
                && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(AttributeValueBigInteger.class, "Invalid Tag for AttributeValue.BigInteger");
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() ||
                !encodingType.getDescription().equals(typeNode.asText())) {
            ctxt.reportInputMismatch(AttributeValueBigInteger.class, "Missing or invalid '@type' attribute for AttributeValue.BigInteger");
            return null;
        }

        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(AttributeValueBigInteger.class,
                    "Missing or non-number 'value' for AttributeValue.BigInteger");
            return null;
        }

        BigInteger value = codec.treeToValue(valueNode, BigInteger.class);
        AttributeValueBigInteger attributeValueBigInteger = AttributeValueBigInteger.of(value);

        KmipSpec spec = KmipContext.getSpec();

        if (!attributeValueBigInteger.isSupported()) {
            ctxt.reportInputMismatch(AttributeValueBigInteger.class, "AttributeValue.BigInteger not supported for spec " + spec);
            return null;
        }

        return attributeValueBigInteger;
    }
}

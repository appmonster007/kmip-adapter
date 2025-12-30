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
import java.nio.ByteBuffer;

public class AttributeValueByteStringXmlDeserializer extends KmipDataTypeXmlDeserializer<AttributeValue.ByteString> {
    private final KmipTag kmipTag = AttributeValue.ByteString.kmipTag;
    private final EncodingType encodingType = AttributeValue.ByteString.encodingType;

    @Override
    public AttributeValue.ByteString deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(AttributeValue.ByteString.class, "Expected XML object for AttributeValue.ByteString");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
                && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(AttributeValue.ByteString.class, "Invalid Tag for AttributeValue.ByteString");
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() ||
                !encodingType.getDescription().equals(typeNode.asText())) {
            ctxt.reportInputMismatch(AttributeValue.ByteString.class, "Missing or invalid '@type' attribute for AttributeValue.ByteString");
            return null;
        }

        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(AttributeValue.ByteString.class,
                    "Missing or non-text 'value' for AttributeValue.ByteString");
            return null;
        }

        ByteBuffer value = codec.treeToValue(valueNode, ByteBuffer.class);
        AttributeValue.ByteString attributeValueByteString = AttributeValue.ByteString.of(value);

        KmipSpec spec = KmipContext.getSpec();

        if (!attributeValueByteString.isSupported()) {
            ctxt.reportInputMismatch(AttributeValue.ByteString.class, "AttributeValue.ByteString not supported for spec " + spec);
            return null;
        }

        return attributeValueByteString;
    }
}

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
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.time.OffsetDateTime;

public class AttributeValueXmlDeserializer extends KmipDataTypeXmlDeserializer<AttributeValue> {
    private final KmipTag kmipTag = AttributeValue.kmipTag;

    @Override
    public AttributeValue deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(AttributeValue.class, "Expected XML object for AttributeValue");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
                && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(AttributeValue.class, "Invalid Tag for AttributeValue");
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual()) {
            ctxt.reportInputMismatch(AttributeValue.class, "Missing or invalid '@type' attribute for AttributeValue");
            return null;
        }
        EncodingType encodingType = EncodingType.fromName(typeNode.asText()).get();

        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(AttributeValue.class,
                    "Missing or non-text 'value' for AttributeValue");
            return null;
        }

        Object obj;
        switch (encodingType) {
            case INTEGER, ENUMERATION, INTERVAL -> obj = valueNode.intValue();
            case BOOLEAN -> obj = valueNode.asBoolean();
            case DATE_TIME -> obj = OffsetDateTime.parse(valueNode.asText());
            case LONG_INTEGER -> obj = valueNode.longValue();
            case TEXT_STRING -> obj = valueNode.asText();
            case BYTE_STRING -> obj = p.getCodec().treeToValue(valueNode, ByteBuffer.class);
            case BIG_INTEGER -> obj = p.getCodec().treeToValue(valueNode, BigInteger.class);
            default -> throw new IllegalArgumentException("Unsupported encoding type: " + encodingType);
        }
        AttributeValue attributeValue = AttributeValue.builder().encodingType(encodingType).value(obj).build();

        KmipSpec spec = KmipContext.getSpec();
        if (!attributeValue.isSupportedFor(spec)) {
            ctxt.reportInputMismatch(AttributeValue.class, "AttributeValue not supported for spec " + spec);
            return null;
        }

        return attributeValue;
    }
}

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

public class AttributeValueIntervalXmlDeserializer extends KmipDataTypeXmlDeserializer<AttributeValue.Interval> {
    private final KmipTag kmipTag = AttributeValue.Interval.kmipTag;
    private final EncodingType encodingType = AttributeValue.Interval.encodingType;

    @Override
    public AttributeValue.Interval deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(AttributeValue.Interval.class, "Expected XML object for AttributeValue.Interval");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
                && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(AttributeValue.Interval.class, "Invalid Tag for AttributeValue.Interval");
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() ||
                !encodingType.getDescription().equals(typeNode.asText())) {
            ctxt.reportInputMismatch(AttributeValue.Interval.class, "Missing or invalid '@type' attribute for AttributeValue.Interval");
            return null;
        }

        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(AttributeValue.Interval.class,
                    "Missing or non-number 'value' for AttributeValue.Interval");
            return null;
        }

        java.lang.Integer value = codec.treeToValue(valueNode, java.lang.Integer.class);
        AttributeValue.Interval attributeValueInterval = AttributeValue.Interval.of(value);

        KmipSpec spec = KmipContext.getSpec();

        if (!attributeValueInterval.isSupported()) {
            ctxt.reportInputMismatch(AttributeValue.Interval.class, "AttributeValue.Interval not supported for spec " + spec);
            return null;
        }

        return attributeValueInterval;
    }
}

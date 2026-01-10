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
import org.purpleBean.kmip.common.AttributeValueInterval;

import java.io.IOException;

public class AttributeValueIntervalXmlDeserializer extends KmipDataTypeXmlDeserializer<AttributeValueInterval> {
    private final KmipTag kmipTag = AttributeValueInterval.kmipTag;
    private final EncodingType encodingType = AttributeValueInterval.encodingType;

    @Override
    public AttributeValueInterval deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(AttributeValueInterval.class, "Expected XML object for AttributeValue.Interval");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
                && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(AttributeValueInterval.class, "Invalid Tag for AttributeValue.Interval");
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() ||
                !encodingType.getDescription().equals(typeNode.asText())) {
            ctxt.reportInputMismatch(AttributeValueInterval.class, "Missing or invalid '@type' attribute for AttributeValue.Interval");
            return null;
        }

        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(AttributeValueInterval.class,
                    "Missing or non-number 'value' for AttributeValue.Interval");
            return null;
        }

        java.lang.Integer value = codec.treeToValue(valueNode, java.lang.Integer.class);
        AttributeValueInterval attributeValueInterval = AttributeValueInterval.of(value);

        KmipSpec spec = KmipContext.getSpec();

        if (!attributeValueInterval.isSupported()) {
            ctxt.reportInputMismatch(AttributeValueInterval.class, "AttributeValue.Interval not supported for spec " + spec);
            return null;
        }

        return attributeValueInterval;
    }
}

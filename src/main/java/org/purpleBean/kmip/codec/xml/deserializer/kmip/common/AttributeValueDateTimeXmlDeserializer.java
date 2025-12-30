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
import java.time.OffsetDateTime;

public class AttributeValueDateTimeXmlDeserializer extends KmipDataTypeXmlDeserializer<AttributeValue.DateTime> {
    private final KmipTag kmipTag = AttributeValue.DateTime.kmipTag;
    private final EncodingType encodingType = AttributeValue.DateTime.encodingType;

    @Override
    public AttributeValue.DateTime deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(AttributeValue.DateTime.class, "Expected XML object for AttributeValue.DateTime");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
                && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(AttributeValue.DateTime.class, "Invalid Tag for AttributeValue.DateTime");
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() ||
                !encodingType.getDescription().equals(typeNode.asText())) {
            ctxt.reportInputMismatch(AttributeValue.DateTime.class, "Missing or invalid '@type' attribute for AttributeValue.DateTime");
            return null;
        }

        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(AttributeValue.DateTime.class,
                    "Missing or non-text 'value' for AttributeValue.DateTime");
            return null;
        }

        OffsetDateTime value = codec.treeToValue(valueNode, OffsetDateTime.class);
        AttributeValue.DateTime attributeValueDateTime = AttributeValue.DateTime.of(value);

        KmipSpec spec = KmipContext.getSpec();

        if (!attributeValueDateTime.isSupported()) {
            ctxt.reportInputMismatch(AttributeValue.DateTime.class, "AttributeValue.DateTime not supported for spec " + spec);
            return null;
        }

        return attributeValueDateTime;
    }
}

package org.purpleBean.kmip.codec.xml.deserializer.kmip.common;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
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
        if (p.currentToken() == null) {
            p.nextToken();
        }

        String currentName;
        if (p instanceof FromXmlParser xmlParser) {
            currentName = xmlParser.getStaxReader().getLocalName();
        } else {
            currentName = (String) ctxt.getAttribute("tag");
        }

        if (!kmipTag.getDescription().equalsIgnoreCase(currentName)) {
            ctxt.reportInputMismatch(AttributeValueInterval.class, "Invalid Tag for AttributeValue.Interval");
            return null;
        }

        if (p.currentToken() != JsonToken.START_OBJECT) {
            p.nextToken();
        }

        AttributeValueInterval.AttributeValueIntervalBuilder builder = AttributeValueInterval.builder();

        while (p.nextToken() != JsonToken.END_OBJECT) {
            if (p.currentToken() == JsonToken.FIELD_NAME) {
                String fieldName = p.currentName();

                p.nextToken(); // Move to the value token
                if ("type".equalsIgnoreCase(fieldName)) {
                    String type = p.getText();
                    if (!encodingType.getDescription().equals(type)) {
                        ctxt.reportInputMismatch(AttributeValueInterval.class, "Missing or invalid 'type' attribute for AttributeValue.Interval");
                        return null;
                    }
                }
                if ("value".equalsIgnoreCase(fieldName)) {
                    if (p.hasTextCharacters()) {
                        ctxt.reportInputMismatch(AttributeValueInterval.class,
                                "Missing or non-number 'value' for AttributeValue.Interval");
                        return null;
                    }
                    builder.value(ctxt.readValue(p, Integer.class));
                }
            }
        }

        AttributeValueInterval attributeValueInterval = builder.build();

        KmipSpec spec = KmipContext.getSpec();
        if (!attributeValueInterval.isSupported()) {
            ctxt.reportInputMismatch(AttributeValueInterval.class, "AttributeValue.Interval not supported for spec " + spec);
            return null;
        }

        return attributeValueInterval;
    }
}
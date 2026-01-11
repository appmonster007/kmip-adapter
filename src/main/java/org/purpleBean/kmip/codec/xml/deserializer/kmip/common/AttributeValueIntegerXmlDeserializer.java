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
import org.purpleBean.kmip.common.AttributeValueInteger;

import java.io.IOException;

public class AttributeValueIntegerXmlDeserializer extends KmipDataTypeXmlDeserializer<AttributeValueInteger> {
    private final KmipTag kmipTag = AttributeValueInteger.kmipTag;
    private final EncodingType encodingType = AttributeValueInteger.encodingType;

    @Override
    public AttributeValueInteger deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
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
            ctxt.reportInputMismatch(AttributeValueInteger.class, "Invalid Tag for AttributeValue.Integer");
            return null;
        }

        if (p.currentToken() != JsonToken.START_OBJECT) {
            p.nextToken();
        }

        AttributeValueInteger.AttributeValueIntegerBuilder builder = AttributeValueInteger.builder();

        while (p.nextToken() != JsonToken.END_OBJECT) {
            if (p.currentToken() == JsonToken.FIELD_NAME) {
                String fieldName = p.currentName();

                p.nextToken(); // Move to the value token
                if ("type".equalsIgnoreCase(fieldName)) {
                    String type = p.getText();
                    if (!encodingType.getDescription().equals(type)) {
                        ctxt.reportInputMismatch(AttributeValueInteger.class, "Missing or invalid 'type' attribute for AttributeValue.Integer");
                        return null;
                    }
                }
                if ("value".equalsIgnoreCase(fieldName)) {
                    if (p.hasTextCharacters()) {
                        ctxt.reportInputMismatch(AttributeValueInteger.class,
                                "Missing or non-number 'value' for AttributeValue.Integer");
                        return null;
                    }
                    builder.value(Integer.parseInt(p.getText()));
                }
            }
        }

        AttributeValueInteger attributeValueInteger = builder.build();

        KmipSpec spec = KmipContext.getSpec();
        if (!attributeValueInteger.isSupported()) {
            ctxt.reportInputMismatch(AttributeValueInteger.class, "AttributeValue.Integer not supported for spec " + spec);
            return null;
        }

        return attributeValueInteger;
    }
}
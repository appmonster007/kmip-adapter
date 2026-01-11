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
import org.purpleBean.kmip.common.AttributeValueBoolean;

import java.io.IOException;

public class AttributeValueBooleanXmlDeserializer extends KmipDataTypeXmlDeserializer<AttributeValueBoolean> {
    private final KmipTag kmipTag = AttributeValueBoolean.kmipTag;
    private final EncodingType encodingType = AttributeValueBoolean.encodingType;

    @Override
    public AttributeValueBoolean deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
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
            ctxt.reportInputMismatch(AttributeValueBoolean.class, "Invalid Tag for AttributeValue.Boolean");
            return null;
        }

        if (p.currentToken() != JsonToken.START_OBJECT) {
            p.nextToken();
        }

        AttributeValueBoolean.AttributeValueBooleanBuilder builder = AttributeValueBoolean.builder();

        while (p.nextToken() != JsonToken.END_OBJECT) {
            if (p.currentToken() == JsonToken.FIELD_NAME) {
                String fieldName = p.currentName();

                p.nextToken(); // Move to the value token
                if ("type".equalsIgnoreCase(fieldName)) {
                    String type = p.getText();
                    if (!encodingType.getDescription().equals(type)) {
                        ctxt.reportInputMismatch(AttributeValueBoolean.class, "Missing or invalid 'type' attribute for AttributeValue.Boolean");
                        return null;
                    }
                }
                if ("value".equalsIgnoreCase(fieldName)) {
                    if (p.hasTextCharacters()) {
                        ctxt.reportInputMismatch(AttributeValueBoolean.class,
                                "Missing or non-boolean 'value' for AttributeValue.Boolean");
                        return null;
                    }
                    builder.value(ctxt.readValue(p, Boolean.class));
                }
            }
        }

        AttributeValueBoolean attributeValueBoolean = builder.build();

        KmipSpec spec = KmipContext.getSpec();
        if (!attributeValueBoolean.isSupported()) {
            ctxt.reportInputMismatch(AttributeValueBoolean.class, "AttributeValue.Boolean not supported for spec " + spec);
            return null;
        }

        return attributeValueBoolean;
    }
}
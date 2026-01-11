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
import org.purpleBean.kmip.common.AttributeValueTextString;

import java.io.IOException;

public class AttributeValueTextStringXmlDeserializer extends KmipDataTypeXmlDeserializer<AttributeValueTextString> {
    private final KmipTag kmipTag = AttributeValueTextString.kmipTag;
    private final EncodingType encodingType = AttributeValueTextString.encodingType;

    @Override
    public AttributeValueTextString deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
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
            ctxt.reportInputMismatch(AttributeValueTextString.class, "Invalid Tag for AttributeValue.TextString");
            return null;
        }

        if (p.currentToken() != JsonToken.START_OBJECT) {
            p.nextToken();
        }

        AttributeValueTextString.AttributeValueTextStringBuilder builder = AttributeValueTextString.builder();

        while (p.nextToken() != JsonToken.END_OBJECT) {
            if (p.currentToken() == JsonToken.FIELD_NAME) {
                String fieldName = p.currentName();

                p.nextToken(); // Move to the value token
                if ("type".equalsIgnoreCase(fieldName)) {
                    String type = p.getText();
                    if (!encodingType.getDescription().equals(type)) {
                        ctxt.reportInputMismatch(AttributeValueTextString.class, "Missing or invalid 'type' attribute for AttributeValue.TextString");
                        return null;
                    }
                }
                if ("value".equalsIgnoreCase(fieldName)) {
                    if (p.hasTextCharacters()) {
                        ctxt.reportInputMismatch(AttributeValueTextString.class,
                                "Missing or non-text 'value' for AttributeValue.TextString");
                        return null;
                    }
                    builder.value(p.getText());
                }
            }
        }

        AttributeValueTextString attributeValueTextString = builder.build();

        KmipSpec spec = KmipContext.getSpec();
        if (!attributeValueTextString.isSupported()) {
            ctxt.reportInputMismatch(AttributeValueTextString.class, "AttributeValue.TextString not supported for spec " + spec);
            return null;
        }

        return attributeValueTextString;
    }
}
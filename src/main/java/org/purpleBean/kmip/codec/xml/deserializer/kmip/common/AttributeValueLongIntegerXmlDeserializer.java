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
import org.purpleBean.kmip.common.AttributeValueLongInteger;

import java.io.IOException;

public class AttributeValueLongIntegerXmlDeserializer extends KmipDataTypeXmlDeserializer<AttributeValueLongInteger> {
    private final KmipTag kmipTag = AttributeValueLongInteger.kmipTag;
    private final EncodingType encodingType = AttributeValueLongInteger.encodingType;

    @Override
    public AttributeValueLongInteger deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
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
            ctxt.reportInputMismatch(AttributeValueLongInteger.class, "Invalid Tag for AttributeValue.LongInteger");
            return null;
        }

        if (p.currentToken() != JsonToken.START_OBJECT) {
            p.nextToken();
        }

        AttributeValueLongInteger.AttributeValueLongIntegerBuilder builder = AttributeValueLongInteger.builder();

        while (p.nextToken() != JsonToken.END_OBJECT) {
            if (p.currentToken() == JsonToken.FIELD_NAME) {
                String fieldName = p.currentName();

                p.nextToken(); // Move to the value token
                if ("type".equalsIgnoreCase(fieldName)) {
                    String type = p.getText();
                    if (!encodingType.getDescription().equals(type)) {
                        ctxt.reportInputMismatch(AttributeValueLongInteger.class, "Missing or invalid 'type' attribute for AttributeValue.LongInteger");
                        return null;
                    }
                }
                if ("value".equalsIgnoreCase(fieldName)) {
                    if (p.hasTextCharacters()) {
                        ctxt.reportInputMismatch(AttributeValueLongInteger.class,
                                "Missing or non-number 'value' for AttributeValue.LongInteger");
                        return null;
                    }
                    builder.value(ctxt.readValue(p, Long.class));
                }
            }
        }

        AttributeValueLongInteger attributeValueLongInteger = builder.build();

        KmipSpec spec = KmipContext.getSpec();
        if (!attributeValueLongInteger.isSupported()) {
            ctxt.reportInputMismatch(AttributeValueLongInteger.class, "AttributeValue.LongInteger not supported for spec " + spec);
            return null;
        }

        return attributeValueLongInteger;
    }
}
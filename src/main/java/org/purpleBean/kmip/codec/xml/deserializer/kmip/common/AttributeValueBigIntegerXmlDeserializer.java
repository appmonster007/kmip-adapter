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
import org.purpleBean.kmip.common.AttributeValueBigInteger;

import java.io.IOException;
import java.math.BigInteger;

public class AttributeValueBigIntegerXmlDeserializer extends KmipDataTypeXmlDeserializer<AttributeValueBigInteger> {
    private final KmipTag kmipTag = AttributeValueBigInteger.kmipTag;
    private final EncodingType encodingType = AttributeValueBigInteger.encodingType;

    @Override
    public AttributeValueBigInteger deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
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
            ctxt.reportInputMismatch(AttributeValueBigInteger.class, "Invalid Tag for AttributeValue.BigInteger");
            return null;
        }

        if (p.currentToken() != JsonToken.START_OBJECT) {
            p.nextToken();
        }

        AttributeValueBigInteger.AttributeValueBigIntegerBuilder builder = AttributeValueBigInteger.builder();

        while (p.nextToken() != JsonToken.END_OBJECT) {
            if (p.currentToken() == JsonToken.FIELD_NAME) {
                String fieldName = p.currentName();

                p.nextToken(); // Move to the value token
                if ("type".equalsIgnoreCase(fieldName)) {
                    String type = p.getText();
                    if (!encodingType.getDescription().equals(type)) {
                        ctxt.reportInputMismatch(AttributeValueBigInteger.class, "Missing or invalid 'type' attribute for AttributeValue.BigInteger");
                        return null;
                    }
                }
                if ("value".equalsIgnoreCase(fieldName)) {
                    if (p.hasTextCharacters()) {
                        ctxt.reportInputMismatch(AttributeValueBigInteger.class,
                                "Missing or non-number 'value' for AttributeValue.BigInteger");
                        return null;
                    }
                    builder.value(ctxt.readValue(p, BigInteger.class));
                }
            }
        }

        AttributeValueBigInteger attributeValueBigInteger = builder.build();

        KmipSpec spec = KmipContext.getSpec();
        if (!attributeValueBigInteger.isSupported()) {
            ctxt.reportInputMismatch(AttributeValueBigInteger.class, "AttributeValue.BigInteger not supported for spec " + spec);
            return null;
        }

        return attributeValueBigInteger;
    }
}
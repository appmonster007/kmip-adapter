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
import org.purpleBean.kmip.common.AttributeValueByteString;

import java.io.IOException;
import java.nio.ByteBuffer;

public class AttributeValueByteStringXmlDeserializer extends KmipDataTypeXmlDeserializer<AttributeValueByteString> {
    private final KmipTag kmipTag = AttributeValueByteString.kmipTag;
    private final EncodingType encodingType = AttributeValueByteString.encodingType;

    @Override
    public AttributeValueByteString deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
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
            ctxt.reportInputMismatch(AttributeValueByteString.class, "Invalid Tag for AttributeValue.ByteString");
            return null;
        }

        if (p.currentToken() != JsonToken.START_OBJECT) {
            p.nextToken();
        }

        AttributeValueByteString.AttributeValueByteStringBuilder builder = AttributeValueByteString.builder();

        while (p.nextToken() != JsonToken.END_OBJECT) {
            if (p.currentToken() == JsonToken.FIELD_NAME) {
                String fieldName = p.currentName();

                p.nextToken(); // Move to the value token
                if ("type".equalsIgnoreCase(fieldName)) {
                    String type = p.getText();
                    if (!encodingType.getDescription().equals(type)) {
                        ctxt.reportInputMismatch(AttributeValueByteString.class, "Missing or invalid 'type' attribute for AttributeValue.ByteString");
                        return null;
                    }
                }
                if ("value".equalsIgnoreCase(fieldName)) {
                    if (p.hasTextCharacters()) {
                        ctxt.reportInputMismatch(AttributeValueByteString.class,
                                "Missing or non-text 'value' for AttributeValue.ByteString");
                        return null;
                    }
                    builder.value(ctxt.readValue(p, ByteBuffer.class));
                }
            }
        }

        AttributeValueByteString attributeValueByteString = builder.build();

        KmipSpec spec = KmipContext.getSpec();
        if (!attributeValueByteString.isSupported()) {
            ctxt.reportInputMismatch(AttributeValueByteString.class, "AttributeValue.ByteString not supported for spec " + spec);
            return null;
        }

        return attributeValueByteString;
    }
}
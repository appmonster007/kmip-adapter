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
import org.purpleBean.kmip.common.AttributeName;

import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * XML deserializer for AttributeName.
 */
public class AttributeNameXmlDeserializer extends KmipDataTypeXmlDeserializer<AttributeName> {
    private final KmipTag kmipTag = new KmipTag(KmipTag.Standard.ATTRIBUTE_NAME);
    private final EncodingType encodingType = EncodingType.TEXT_STRING;

    @Override
    public AttributeName deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
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
            ctxt.reportInputMismatch(AttributeName.class, "Invalid Tag for AttributeName");
            return null;
        }

        if (p.currentToken() != JsonToken.START_OBJECT) {
            p.nextToken();
        }

        AttributeName.AttributeNameBuilder builder = AttributeName.builder();

        while (p.nextToken() != JsonToken.END_OBJECT) {
            if (p.currentToken() == JsonToken.FIELD_NAME) {
                String fieldName = p.currentName();

                p.nextToken(); // Move to the value token
                if ("type".equalsIgnoreCase(fieldName)) {
                    String type = p.getText();
                    if (!encodingType.getDescription().equals(type)) {
                        ctxt.reportInputMismatch(AttributeName.class, "Missing or invalid 'type' attribute for AttributeName");
                        return null;
                    }
                }
                if ("value".equalsIgnoreCase(fieldName)) {
                    if (p.hasTextCharacters()) {
                        ctxt.reportInputMismatch(AttributeName.class,
                                "Missing or non-text 'value' for AttributeName");
                        return null;
                    }
                    builder.value(p.getText());
                }
            }
        }

        AttributeName datatype = builder.build();

        KmipSpec spec = KmipContext.getSpec();
        if (!datatype.isSupported()) {
            throw new NoSuchElementException(
                    String.format("AttributeName '%s' not supported for spec %s", kmipTag.getDescription(), spec));

        }
        return datatype;
    }
}
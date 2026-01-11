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
import org.purpleBean.kmip.common.KeyValuePresent;

import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * XML deserializer for KeyValuePresent.
 */
public class KeyValuePresentXmlDeserializer extends KmipDataTypeXmlDeserializer<KeyValuePresent> {
    private final KmipTag kmipTag = KeyValuePresent.kmipTag;
    private final EncodingType encodingType = KeyValuePresent.encodingType;

    @Override
    public KeyValuePresent deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
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
            ctxt.reportInputMismatch(KeyValuePresent.class, "Invalid Tag for KeyValuePresent");
            return null;
        }

        if (p.currentToken() != JsonToken.START_OBJECT) {
            p.nextToken();
        }

        KeyValuePresent.KeyValuePresentBuilder builder = KeyValuePresent.builder();

        while (p.nextToken() != JsonToken.END_OBJECT) {
            if (p.currentToken() == JsonToken.FIELD_NAME) {
                String fieldName = p.currentName();

                p.nextToken(); // Move to the value token
                if ("type".equalsIgnoreCase(fieldName)) {
                    String type = p.getText();
                    if (!encodingType.getDescription().equals(type)) {
                        ctxt.reportInputMismatch(KeyValuePresent.class, "Missing or invalid 'type' attribute for KeyValuePresent");
                        return null;
                    }
                }
                if ("value".equalsIgnoreCase(fieldName)) {
                    if (p.hasTextCharacters()) {
                        ctxt.reportInputMismatch(KeyValuePresent.class, "Missing or non-text '@value' attribute for KeyValuePresent");
                        return null;
                    }
                    builder.value(Boolean.valueOf(p.getText()));
                }
            }
        }

        KeyValuePresent attribute = builder.build();

        KmipSpec spec = KmipContext.getSpec();
        if (!attribute.isSupported()) {
            throw new NoSuchElementException(
                    String.format("KeyValuePresent not supported for spec %s", spec));
        }

        return attribute;
    }
}
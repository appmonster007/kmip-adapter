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
import org.purpleBean.kmip.common.KeyPartIdentifier;

import java.io.IOException;

public class KeyPartIdentifierXmlDeserializer extends KmipDataTypeXmlDeserializer<KeyPartIdentifier> {
    private final KmipTag kmipTag = KeyPartIdentifier.kmipTag;
    private final EncodingType encodingType = KeyPartIdentifier.encodingType;

    @Override
    public KeyPartIdentifier deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
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
            ctxt.reportInputMismatch(KeyPartIdentifier.class, "Invalid Tag for KeyPartIdentifier");
            return null;
        }

        if (p.currentToken() != JsonToken.START_OBJECT) {
            p.nextToken();
        }

        KeyPartIdentifier.KeyPartIdentifierBuilder builder = KeyPartIdentifier.builder();

        while (p.nextToken() != JsonToken.END_OBJECT) {
            if (p.currentToken() == JsonToken.FIELD_NAME) {
                String fieldName = p.currentName();

                p.nextToken(); // Move to the value token
                if ("type".equalsIgnoreCase(fieldName)) {
                    String type = p.getText();
                    if (!encodingType.getDescription().equals(type)) {
                        ctxt.reportInputMismatch(KeyPartIdentifier.class, "Missing or invalid 'type' attribute for KeyPartIdentifier");
                        return null;
                    }
                }
                if ("value".equalsIgnoreCase(fieldName)) {
                    if (p.hasTextCharacters()) {
                        ctxt.reportInputMismatch(KeyPartIdentifier.class,
                                "Missing or non-number 'value' for KeyPartIdentifier");
                        return null;
                    }
                    builder.value(Integer.parseInt(p.getText()));
                }
            }
        }

        KeyPartIdentifier keyPartIdentifier = builder.build();

        KmipSpec spec = KmipContext.getSpec();
        if (!keyPartIdentifier.isSupported()) {
            ctxt.reportInputMismatch(KeyPartIdentifier.class, "KeyPartIdentifier not supported for spec " + spec);
            return null;
        }

        return keyPartIdentifier;
    }
}
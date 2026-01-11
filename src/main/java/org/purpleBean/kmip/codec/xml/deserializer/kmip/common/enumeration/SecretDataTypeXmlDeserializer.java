package org.purpleBean.kmip.codec.xml.deserializer.kmip.common.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.dataformat.xml.deser.FromXmlParser;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.xml.deserializer.kmip.KmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.common.enumeration.SecretDataType;

import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * XML deserializer for SecretDataType.
 */
public class SecretDataTypeXmlDeserializer extends KmipDataTypeXmlDeserializer<SecretDataType> {
    private final KmipTag kmipTag = SecretDataType.kmipTag;
    private final EncodingType encodingType = SecretDataType.encodingType;

    @Override
    public SecretDataType deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
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
            ctxt.reportInputMismatch(SecretDataType.class, "Invalid Tag for SecretDataType");
            return null;
        }

        if (p.currentToken() != JsonToken.START_OBJECT) {
            p.nextToken();
        }

        String description = null;

        while (p.nextToken() != JsonToken.END_OBJECT) {
            if (p.currentToken() == JsonToken.FIELD_NAME) {
                String fieldName = p.currentName();

                p.nextToken(); // Move to the value token
                if ("type".equalsIgnoreCase(fieldName)) {
                    String type = p.getText();
                    if (!encodingType.getDescription().equals(type)) {
                        ctxt.reportInputMismatch(SecretDataType.class, "Missing or invalid 'type' attribute for SecretDataType");
                        return null;
                    }
                }
                if ("value".equalsIgnoreCase(fieldName)) {
                    if (p.hasTextCharacters()) {
                        ctxt.reportInputMismatch(SecretDataType.class,
                                "Missing or non-text 'value' for SecretDataType");
                        return null;
                    }
                    description = p.getText();
                }
            }
        }

        if (description == null) {
            ctxt.reportInputMismatch(SecretDataType.class, "Missing 'value' for SecretDataType");
            return null;
        }

        KmipSpec spec = KmipContext.getSpec();

        SecretDataType secretDataType = new SecretDataType(SecretDataType.fromName(description));
        if (!secretDataType.isSupported()) {
            throw new NoSuchElementException(
                    String.format("SecretDataType '%s' not supported for spec %s", description, spec));
        }

        return secretDataType;
    }
}

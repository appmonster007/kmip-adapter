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
import org.purpleBean.kmip.common.enumeration.OtpAlgorithm;

import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * XML deserializer for OtpAlgorithm.
 */
public class OtpAlgorithmXmlDeserializer extends KmipDataTypeXmlDeserializer<OtpAlgorithm> {
    private final KmipTag kmipTag = OtpAlgorithm.kmipTag;
    private final EncodingType encodingType = OtpAlgorithm.encodingType;

    @Override
    public OtpAlgorithm deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
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
            ctxt.reportInputMismatch(OtpAlgorithm.class, "Invalid Tag for OtpAlgorithm");
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
                        ctxt.reportInputMismatch(OtpAlgorithm.class, "Missing or invalid 'type' attribute for OtpAlgorithm");
                        return null;
                    }
                }
                if ("value".equalsIgnoreCase(fieldName)) {
                    if (p.hasTextCharacters()) {
                        ctxt.reportInputMismatch(OtpAlgorithm.class,
                                "Missing or non-text 'value' for OtpAlgorithm");
                        return null;
                    }
                    description = p.getText();
                }
            }
        }

        if (description == null) {
            ctxt.reportInputMismatch(OtpAlgorithm.class, "Missing 'value' for OtpAlgorithm");
            return null;
        }

        KmipSpec spec = KmipContext.getSpec();

        OtpAlgorithm otpalgorithm = new OtpAlgorithm(OtpAlgorithm.fromName(description));
        if (!otpalgorithm.isSupported()) {
            throw new NoSuchElementException(
                    String.format("OtpAlgorithm '%s' not supported for spec %s", description, spec));
        }

        return otpalgorithm;
    }
}
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
import org.purpleBean.kmip.common.enumeration.RngAlgorithm;

import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * XML deserializer for RngAlgorithm.
 */
public class RngAlgorithmXmlDeserializer extends KmipDataTypeXmlDeserializer<RngAlgorithm> {
    private final KmipTag kmipTag = RngAlgorithm.kmipTag;
    private final EncodingType encodingType = RngAlgorithm.encodingType;

    @Override
    public RngAlgorithm deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
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
            ctxt.reportInputMismatch(RngAlgorithm.class, "Invalid Tag for RngAlgorithm");
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
                        ctxt.reportInputMismatch(RngAlgorithm.class, "Missing or invalid 'type' attribute for RngAlgorithm");
                        return null;
                    }
                }
                if ("value".equalsIgnoreCase(fieldName)) {
                    if (p.hasTextCharacters()) {
                        ctxt.reportInputMismatch(RngAlgorithm.class,
                                "Missing or non-text 'value' for RngAlgorithm");
                        return null;
                    }
                    description = p.getText();
                }
            }
        }

        if (description == null) {
            ctxt.reportInputMismatch(RngAlgorithm.class, "Missing 'value' for RngAlgorithm");
            return null;
        }

        KmipSpec spec = KmipContext.getSpec();

        RngAlgorithm rngAlgorithm = new RngAlgorithm(RngAlgorithm.fromName(description));
        if (!rngAlgorithm.isSupported()) {
            throw new NoSuchElementException(
                    String.format("RngAlgorithm '%s' not supported for spec %s", description, spec));
        }

        return rngAlgorithm;
    }
}

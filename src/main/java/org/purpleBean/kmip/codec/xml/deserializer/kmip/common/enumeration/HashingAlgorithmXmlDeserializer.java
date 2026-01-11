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
import org.purpleBean.kmip.common.enumeration.HashingAlgorithm;

import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * XML deserializer for HashingAlgorithm.
 */
public class HashingAlgorithmXmlDeserializer extends KmipDataTypeXmlDeserializer<HashingAlgorithm> {
    private final KmipTag kmipTag = HashingAlgorithm.kmipTag;
    private final EncodingType encodingType = HashingAlgorithm.encodingType;

    @Override
    public HashingAlgorithm deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
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
            ctxt.reportInputMismatch(HashingAlgorithm.class, "Invalid Tag for HashingAlgorithm");
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
                        ctxt.reportInputMismatch(HashingAlgorithm.class, "Missing or invalid 'type' attribute for HashingAlgorithm");
                        return null;
                    }
                }
                if ("value".equalsIgnoreCase(fieldName)) {
                    if (p.hasTextCharacters()) {
                        ctxt.reportInputMismatch(HashingAlgorithm.class,
                                "Missing or non-text 'value' for HashingAlgorithm");
                        return null;
                    }
                    description = p.getText();
                }
            }
        }

        if (description == null) {
            ctxt.reportInputMismatch(HashingAlgorithm.class, "Missing 'value' for HashingAlgorithm");
            return null;
        }

        KmipSpec spec = KmipContext.getSpec();

        HashingAlgorithm hashingAlgorithm = new HashingAlgorithm(HashingAlgorithm.fromName(description));
        if (!hashingAlgorithm.isSupported()) {
            throw new NoSuchElementException(
                    String.format("HashingAlgorithm '%s' not supported for spec %s", description, spec));
        }

        return hashingAlgorithm;
    }
}

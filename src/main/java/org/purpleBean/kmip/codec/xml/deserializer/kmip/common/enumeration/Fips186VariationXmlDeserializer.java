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
import org.purpleBean.kmip.common.enumeration.Fips186Variation;

import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * XML deserializer for Fips186Variation.
 */
public class Fips186VariationXmlDeserializer extends KmipDataTypeXmlDeserializer<Fips186Variation> {
    private final KmipTag kmipTag = Fips186Variation.kmipTag;
    private final EncodingType encodingType = Fips186Variation.encodingType;

    @Override
    public Fips186Variation deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
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
            ctxt.reportInputMismatch(Fips186Variation.class, "Invalid Tag for Fips186Variation");
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
                        ctxt.reportInputMismatch(Fips186Variation.class, "Missing or invalid 'type' attribute for Fips186Variation");
                        return null;
                    }
                }
                if ("value".equalsIgnoreCase(fieldName)) {
                    if (p.hasTextCharacters()) {
                        ctxt.reportInputMismatch(Fips186Variation.class,
                                "Missing or non-text 'value' for Fips186Variation");
                        return null;
                    }
                    description = p.getText();
                }
            }
        }

        if (description == null) {
            ctxt.reportInputMismatch(Fips186Variation.class, "Missing 'value' for Fips186Variation");
            return null;
        }

        KmipSpec spec = KmipContext.getSpec();

        Fips186Variation fips186variation = new Fips186Variation(Fips186Variation.fromName(description));
        if (!fips186variation.isSupported()) {
            throw new NoSuchElementException(
                    String.format("Fips186Variation '%s' not supported for spec %s", description, spec));
        }

        return fips186variation;
    }
}
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
import org.purpleBean.kmip.common.PrimeFieldSize;

import java.io.IOException;
import java.math.BigInteger;

public class PrimeFieldSizeXmlDeserializer extends KmipDataTypeXmlDeserializer<PrimeFieldSize> {
    private final KmipTag kmipTag = PrimeFieldSize.kmipTag;
    private final EncodingType encodingType = PrimeFieldSize.encodingType;

    @Override
    public PrimeFieldSize deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
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
            ctxt.reportInputMismatch(PrimeFieldSize.class, "Invalid Tag for PrimeFieldSize");
            return null;
        }

        if (p.currentToken() != JsonToken.START_OBJECT) {
            p.nextToken();
        }

        PrimeFieldSize.PrimeFieldSizeBuilder builder = PrimeFieldSize.builder();

        while (p.nextToken() != JsonToken.END_OBJECT) {
            if (p.currentToken() == JsonToken.FIELD_NAME) {
                String fieldName = p.currentName();

                p.nextToken(); // Move to the value token
                if ("type".equalsIgnoreCase(fieldName)) {
                    String type = p.getText();
                    if (!encodingType.getDescription().equals(type)) {
                        ctxt.reportInputMismatch(PrimeFieldSize.class, "Missing or invalid 'type' attribute for PrimeFieldSize");
                        return null;
                    }
                }
                if ("value".equalsIgnoreCase(fieldName)) {
                    if (p.hasTextCharacters()) {
                        ctxt.reportInputMismatch(PrimeFieldSize.class,
                                "Missing or non-text 'value' for PrimeFieldSize");
                        return null;
                    }
                    builder.value(ctxt.readValue(p, BigInteger.class));
                }
            }
        }

        PrimeFieldSize primeFieldSize = builder.build();

        KmipSpec spec = KmipContext.getSpec();
        if (!primeFieldSize.isSupported()) {
            ctxt.reportInputMismatch(PrimeFieldSize.class, "PrimeFieldSize not supported for spec " + spec);
            return null;
        }

        return primeFieldSize;
    }
}
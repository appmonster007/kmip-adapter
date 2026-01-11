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
import org.purpleBean.kmip.common.PrivateExponent;

import java.io.IOException;
import java.math.BigInteger;

public class PrivateExponentXmlDeserializer extends KmipDataTypeXmlDeserializer<PrivateExponent> {
    private final KmipTag kmipTag = PrivateExponent.kmipTag;
    private final EncodingType encodingType = PrivateExponent.encodingType;

    @Override
    public PrivateExponent deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
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
            ctxt.reportInputMismatch(PrivateExponent.class, "Invalid Tag for PrivateExponent");
            return null;
        }

        if (p.currentToken() != JsonToken.START_OBJECT) {
            p.nextToken();
        }

        PrivateExponent.PrivateExponentBuilder builder = PrivateExponent.builder();

        while (p.nextToken() != JsonToken.END_OBJECT) {
            if (p.currentToken() == JsonToken.FIELD_NAME) {
                String fieldName = p.currentName();

                p.nextToken(); // Move to the value token
                if ("type".equalsIgnoreCase(fieldName)) {
                    String type = p.getText();
                    if (!encodingType.getDescription().equals(type)) {
                        ctxt.reportInputMismatch(PrivateExponent.class, "Missing or invalid 'type' attribute for PrivateExponent");
                        return null;
                    }
                }
                if ("value".equalsIgnoreCase(fieldName)) {
                    if (p.hasTextCharacters()) {
                        ctxt.reportInputMismatch(PrivateExponent.class,
                                "Missing or non-text 'value' for PrivateExponent");
                        return null;
                    }
                    builder.value(ctxt.readValue(p, BigInteger.class));
                }
            }
        }

        PrivateExponent privateExponent = builder.build();

        KmipSpec spec = KmipContext.getSpec();
        if (!privateExponent.isSupported()) {
            ctxt.reportInputMismatch(PrivateExponent.class, "PrivateExponent not supported for spec " + spec);
            return null;
        }

        return privateExponent;
    }
}
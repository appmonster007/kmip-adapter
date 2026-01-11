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
import org.purpleBean.kmip.common.CRTCoefficient;

import java.io.IOException;
import java.math.BigInteger;

public class CRTCoefficientXmlDeserializer extends KmipDataTypeXmlDeserializer<CRTCoefficient> {
    private final KmipTag kmipTag = CRTCoefficient.kmipTag;
    private final EncodingType encodingType = CRTCoefficient.encodingType;

    @Override
    public CRTCoefficient deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
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
            ctxt.reportInputMismatch(CRTCoefficient.class, "Invalid Tag for CRTCoefficient");
            return null;
        }

        if (p.currentToken() != JsonToken.START_OBJECT) {
            p.nextToken();
        }

        CRTCoefficient.CRTCoefficientBuilder builder = CRTCoefficient.builder();

        while (p.nextToken() != JsonToken.END_OBJECT) {
            if (p.currentToken() == JsonToken.FIELD_NAME) {
                String fieldName = p.currentName();

                p.nextToken(); // Move to the value token
                if ("type".equalsIgnoreCase(fieldName)) {
                    String type = p.getText();
                    if (!encodingType.getDescription().equals(type)) {
                        ctxt.reportInputMismatch(CRTCoefficient.class, "Missing or invalid 'type' attribute for CRTCoefficient");
                        return null;
                    }
                }
                if ("value".equalsIgnoreCase(fieldName)) {
                    if (p.hasTextCharacters()) {
                        ctxt.reportInputMismatch(CRTCoefficient.class,
                                "Missing or non-text 'value' for CRTCoefficient");
                        return null;
                    }
                    builder.value(ctxt.readValue(p, BigInteger.class));
                }
            }
        }

        CRTCoefficient cRTCoefficient = builder.build();

        KmipSpec spec = KmipContext.getSpec();
        if (!cRTCoefficient.isSupported()) {
            ctxt.reportInputMismatch(CRTCoefficient.class, "CRTCoefficient not supported for spec " + spec);
            return null;
        }

        return cRTCoefficient;
    }
}
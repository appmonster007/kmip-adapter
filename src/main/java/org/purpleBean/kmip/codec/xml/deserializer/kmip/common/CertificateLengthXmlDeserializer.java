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
import org.purpleBean.kmip.common.CertificateLength;

import java.io.IOException;

public class CertificateLengthXmlDeserializer extends KmipDataTypeXmlDeserializer<CertificateLength> {
    private final KmipTag kmipTag = CertificateLength.kmipTag;
    private final EncodingType encodingType = CertificateLength.encodingType;

    @Override
    public CertificateLength deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
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
            ctxt.reportInputMismatch(CertificateLength.class, "Invalid Tag for CertificateLength");
            return null;
        }

        if (p.currentToken() != JsonToken.START_OBJECT) {
            p.nextToken();
        }

        CertificateLength.CertificateLengthBuilder builder = CertificateLength.builder();

        while (p.nextToken() != JsonToken.END_OBJECT) {
            if (p.currentToken() == JsonToken.FIELD_NAME) {
                String fieldName = p.currentName();

                p.nextToken(); // Move to the value token
                if ("type".equalsIgnoreCase(fieldName)) {
                    String type = p.getText();
                    if (!encodingType.getDescription().equals(type)) {
                        ctxt.reportInputMismatch(CertificateLength.class, "Missing or invalid 'type' attribute for CertificateLength");
                        return null;
                    }
                }
                if ("value".equalsIgnoreCase(fieldName)) {
                    if (p.hasTextCharacters()) {
                        ctxt.reportInputMismatch(CertificateLength.class,
                                "Missing or non-text 'value' for CertificateLength");
                        return null;
                    }
                    builder.value(Integer.parseInt(p.getText()));
                }
            }
        }

        CertificateLength certificateLength = builder.build();

        KmipSpec spec = KmipContext.getSpec();
        if (!certificateLength.isSupported()) {
            ctxt.reportInputMismatch(CertificateLength.class, "CertificateLength not supported for spec " + spec);
            return null;
        }

        return certificateLength;
    }
}
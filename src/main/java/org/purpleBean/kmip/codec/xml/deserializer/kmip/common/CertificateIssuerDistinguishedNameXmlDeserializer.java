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
import org.purpleBean.kmip.common.CertificateIssuerDistinguishedName;

import java.io.IOException;

public class CertificateIssuerDistinguishedNameXmlDeserializer extends KmipDataTypeXmlDeserializer<CertificateIssuerDistinguishedName> {
    private final KmipTag kmipTag = CertificateIssuerDistinguishedName.kmipTag;
    private final EncodingType encodingType = CertificateIssuerDistinguishedName.encodingType;

    @Override
    public CertificateIssuerDistinguishedName deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
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
            ctxt.reportInputMismatch(CertificateIssuerDistinguishedName.class, "Invalid Tag for CertificateIssuerDistinguishedName");
            return null;
        }

        if (p.currentToken() != JsonToken.START_OBJECT) {
            p.nextToken();
        }

        CertificateIssuerDistinguishedName.CertificateIssuerDistinguishedNameBuilder builder = CertificateIssuerDistinguishedName.builder();

        while (p.nextToken() != JsonToken.END_OBJECT) {
            if (p.currentToken() == JsonToken.FIELD_NAME) {
                String fieldName = p.currentName();

                p.nextToken(); // Move to the value token
                if ("type".equalsIgnoreCase(fieldName)) {
                    String type = p.getText();
                    if (!encodingType.getDescription().equals(type)) {
                        ctxt.reportInputMismatch(CertificateIssuerDistinguishedName.class, "Missing or invalid 'type' attribute for CertificateIssuerDistinguishedName");
                        return null;
                    }
                }
                if ("value".equalsIgnoreCase(fieldName)) {
                    if (p.hasTextCharacters()) {
                        ctxt.reportInputMismatch(CertificateIssuerDistinguishedName.class,
                                "Missing or non-text 'value' for CertificateIssuerDistinguishedName");
                        return null;
                    }
                    builder.value(p.getText());
                }
            }
        }

        CertificateIssuerDistinguishedName certificateIssuerDistinguishedName = builder.build();

        KmipSpec spec = KmipContext.getSpec();
        if (!certificateIssuerDistinguishedName.isSupported()) {
            ctxt.reportInputMismatch(CertificateIssuerDistinguishedName.class, "CertificateIssuerDistinguishedName not supported for spec " + spec);
            return null;
        }

        return certificateIssuerDistinguishedName;
    }
}
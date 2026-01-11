package org.purpleBean.kmip.codec.xml.deserializer.kmip.common.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.dataformat.xml.deser.FromXmlParser;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.xml.deserializer.kmip.KmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.common.CertificateSerialNumber;
import org.purpleBean.kmip.common.IssuerDistinguishedName;
import org.purpleBean.kmip.common.structure.X509CertificateIdentifier;

import java.io.IOException;

public class X509CertificateIdentifierXmlDeserializer extends KmipDataTypeXmlDeserializer<X509CertificateIdentifier> {
    private final KmipTag kmipTag = X509CertificateIdentifier.kmipTag;

    @Override
    public X509CertificateIdentifier deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
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
            ctxt.reportInputMismatch(X509CertificateIdentifier.class, "Invalid Tag for X509CertificateIdentifier");
            return null;
        }

        if (p.currentToken() != JsonToken.START_OBJECT) {
            p.nextToken();
        }

        KmipSpec spec = KmipContext.getSpec();
        X509CertificateIdentifier.X509CertificateIdentifierBuilder builder = X509CertificateIdentifier.builder();

        while (p.nextToken() != null && p.currentToken() != JsonToken.END_OBJECT) {
            String fieldName = p.currentName();
            KmipTag.Value nodeTag = KmipTag.fromName(spec, fieldName);
            if (p.currentToken() == JsonToken.START_OBJECT) {
                p.nextToken();
                setValue(builder, nodeTag, p, ctxt);
            } else if (p.currentToken() == JsonToken.FIELD_NAME) {
                setValue(builder, nodeTag, p, ctxt);
            } else {
                ctxt.reportInputMismatch(X509CertificateIdentifier.class, "Unexpected token: " + p.currentToken());
            }
        }

        X509CertificateIdentifier x509CertificateIdentifier = builder.build();

        if (!x509CertificateIdentifier.isSupported()) {
            ctxt.reportInputMismatch(X509CertificateIdentifier.class, "X509CertificateIdentifier not supported for spec " + spec);
            return null;
        }

        return x509CertificateIdentifier;
    }

    private void setValue(
            X509CertificateIdentifier.X509CertificateIdentifierBuilder builder,
            KmipTag.Value nodeTag,
            JsonParser p,
            DeserializationContext ctxt
    ) throws IOException {
        ctxt.setAttribute("tag", p.currentName());
        switch (nodeTag) {
            case KmipTag.Standard.ISSUER_DISTINGUISHED_NAME ->
                    builder.issuerDistinguishedName(ctxt.readValue(p, IssuerDistinguishedName.class));
            case KmipTag.Standard.CERTIFICATE_SERIAL_NUMBER ->
                    builder.certificateSerialNumber(ctxt.readValue(p, CertificateSerialNumber.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }
}
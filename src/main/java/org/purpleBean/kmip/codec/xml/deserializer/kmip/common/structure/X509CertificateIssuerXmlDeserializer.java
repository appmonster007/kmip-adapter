package org.purpleBean.kmip.codec.xml.deserializer.kmip.common.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.dataformat.xml.deser.FromXmlParser;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.xml.deserializer.kmip.KmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.common.IssuerAlternativeName;
import org.purpleBean.kmip.common.IssuerDistinguishedName;
import org.purpleBean.kmip.common.structure.X509CertificateIssuer;

import java.io.IOException;

public class X509CertificateIssuerXmlDeserializer extends KmipDataTypeXmlDeserializer<X509CertificateIssuer> {
    private final KmipTag kmipTag = X509CertificateIssuer.kmipTag;

    @Override
    public X509CertificateIssuer deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
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
            ctxt.reportInputMismatch(X509CertificateIssuer.class, "Invalid Tag for X509CertificateIssuer");
            return null;
        }

        if (p.currentToken() != JsonToken.START_OBJECT) {
            p.nextToken();
        }

        KmipSpec spec = KmipContext.getSpec();
        X509CertificateIssuer.X509CertificateIssuerBuilder builder = X509CertificateIssuer.builder();

        while (p.nextToken() != null && p.currentToken() != JsonToken.END_OBJECT) {
            String fieldName = p.currentName();
            KmipTag.Value nodeTag = KmipTag.fromName(spec, fieldName);
            if (p.currentToken() == JsonToken.START_OBJECT) {
                p.nextToken();
                setValue(builder, nodeTag, p, ctxt);
            } else if (p.currentToken() == JsonToken.FIELD_NAME) {
                setValue(builder, nodeTag, p, ctxt);
            } else {
                ctxt.reportInputMismatch(X509CertificateIssuer.class, "Unexpected token: " + p.currentToken());
            }
        }

        X509CertificateIssuer x509certificateissuer = builder.build();

        if (!x509certificateissuer.isSupported()) {
            ctxt.reportInputMismatch(X509CertificateIssuer.class, "X509CertificateIssuer not supported for spec " + spec);
            return null;
        }

        return x509certificateissuer;
    }

    private void setValue(
            X509CertificateIssuer.X509CertificateIssuerBuilder builder,
            KmipTag.Value nodeTag,
            JsonParser p,
            DeserializationContext ctxt
    ) throws IOException {
        ctxt.setAttribute("tag", p.currentName());
        switch (nodeTag) {
            case KmipTag.Standard.ISSUER_DISTINGUISHED_NAME ->
                    builder.issuerDistinguishedName(ctxt.readValue(p, IssuerDistinguishedName.class));
            case KmipTag.Standard.ISSUER_ALTERNATIVE_NAME ->
                    builder.issuerAlternativeName(ctxt.readValue(p, IssuerAlternativeName.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }
}
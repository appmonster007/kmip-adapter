package org.purpleBean.kmip.codec.json.deserializer.kmip.common.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.kmip.AbstractKmipStructureJsonDeserializer;
import org.purpleBean.kmip.common.IssuerAlternativeName;
import org.purpleBean.kmip.common.IssuerDistinguishedName;
import org.purpleBean.kmip.common.structure.X509CertificateIssuer;

import java.io.IOException;

public class X509CertificateIssuerJsonDeserializer extends AbstractKmipStructureJsonDeserializer<X509CertificateIssuer, X509CertificateIssuer.X509CertificateIssuerBuilder> {

    public X509CertificateIssuerJsonDeserializer() {
        super(X509CertificateIssuer.kmipTag, X509CertificateIssuer.encodingType);
    }

    @Override
    protected X509CertificateIssuer.X509CertificateIssuerBuilder createBuilder() {
        return X509CertificateIssuer.builder();
    }

    @Override
    protected void setValue(X509CertificateIssuer.X509CertificateIssuerBuilder builder, KmipTag.Value nodeTag, JsonParser p, DeserializationContext ctxt) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.ISSUER_DISTINGUISHED_NAME ->
                    builder.issuerDistinguishedName(ctxt.readValue(p, IssuerDistinguishedName.class));
            case KmipTag.Standard.ISSUER_ALTERNATIVE_NAME ->
                    builder.issuerAlternativeName(ctxt.readValue(p, IssuerAlternativeName.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected X509CertificateIssuer build(X509CertificateIssuer.X509CertificateIssuerBuilder builder) {
        return builder.build();
    }
}
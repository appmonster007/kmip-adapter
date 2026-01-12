package org.purpleBean.kmip.codec.json.deserializer.kmip.common.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.AbstractKmipStructureJsonDeserializer;
import org.purpleBean.kmip.common.CertificateIssuerAlternativeName;
import org.purpleBean.kmip.common.CertificateIssuerDistinguishedName;
import org.purpleBean.kmip.common.structure.CertificateIssuer;

import java.io.IOException;

public class CertificateIssuerJsonDeserializer extends AbstractKmipStructureJsonDeserializer<CertificateIssuer, CertificateIssuer.CertificateIssuerBuilder> {

    public CertificateIssuerJsonDeserializer() {
        super(CertificateIssuer.kmipTag, CertificateIssuer.encodingType);
    }

    @Override
    protected CertificateIssuer.CertificateIssuerBuilder createBuilder() {
        return CertificateIssuer.builder();
    }

    @Override
    protected void setValue(CertificateIssuer.CertificateIssuerBuilder builder, KmipTag.Value nodeTag, JsonParser p, DeserializationContext ctxt) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.CERTIFICATE_ISSUER_DISTINGUISHED_NAME ->
                    builder.certificateIssuerDistinguishedName(ctxt.readValue(p, CertificateIssuerDistinguishedName.class));
            case KmipTag.Standard.CERTIFICATE_ISSUER_ALTERNATIVE_NAME ->
                    builder.certificateIssuerAlternativeName(ctxt.readValue(p, CertificateIssuerAlternativeName.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected CertificateIssuer build(CertificateIssuer.CertificateIssuerBuilder builder) {
        return builder.build();
    }
}
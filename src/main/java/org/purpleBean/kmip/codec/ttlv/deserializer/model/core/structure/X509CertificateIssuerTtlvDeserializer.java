package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.structure;

import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.AbstractKmipStructureTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.structure.X509CertificateIssuer;
import org.purpleBean.kmip.model.core.type.IssuerAlternativeName;
import org.purpleBean.kmip.model.core.type.IssuerDistinguishedName;

import java.io.IOException;
import java.nio.ByteBuffer;

public class X509CertificateIssuerTtlvDeserializer extends AbstractKmipStructureTtlvDeserializer<X509CertificateIssuer, X509CertificateIssuer.X509CertificateIssuerBuilder> {

    public X509CertificateIssuerTtlvDeserializer() {
        super(X509CertificateIssuer.kmipTag);
    }

    @Override
    protected X509CertificateIssuer.X509CertificateIssuerBuilder createBuilder() {
        return X509CertificateIssuer.builder();
    }

    @Override
    protected void setValue(X509CertificateIssuer.X509CertificateIssuerBuilder builder, KmipTag.Value nodeTag, ByteBuffer p, TtlvMapper mapper) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.ISSUER_DISTINGUISHED_NAME ->
                    builder.issuerDistinguishedName(mapper.readValue(p, IssuerDistinguishedName.class));
            case KmipTag.Standard.ISSUER_ALTERNATIVE_NAME ->
                    builder.issuerAlternativeName(mapper.readValue(p, IssuerAlternativeName.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected X509CertificateIssuer build(X509CertificateIssuer.X509CertificateIssuerBuilder builder) {
        return builder.build();
    }

    @Override
    protected EncodingType getEncodingType() {
        return X509CertificateIssuer.encodingType;
    }
}
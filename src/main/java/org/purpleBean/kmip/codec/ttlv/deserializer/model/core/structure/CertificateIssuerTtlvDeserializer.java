package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.structure;

import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipStructureTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.structure.CertificateIssuer;
import org.purpleBean.kmip.model.core.type.CertificateIssuerAlternativeName;
import org.purpleBean.kmip.model.core.type.CertificateIssuerDistinguishedName;

import java.io.IOException;
import java.nio.ByteBuffer;

public class CertificateIssuerTtlvDeserializer extends AbstractKmipStructureTtlvDeserializer<CertificateIssuer, CertificateIssuer.CertificateIssuerBuilder> {

    public CertificateIssuerTtlvDeserializer() {
        super(CertificateIssuer.kmipTag);
    }

    @Override
    protected CertificateIssuer.CertificateIssuerBuilder createBuilder() {
        return CertificateIssuer.builder();
    }

    @Override
    protected void setValue(CertificateIssuer.CertificateIssuerBuilder builder, KmipTag.Value nodeTag, ByteBuffer p, TtlvMapper mapper) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.CERTIFICATE_ISSUER_DISTINGUISHED_NAME ->
                    builder.certificateIssuerDistinguishedName(mapper.readValue(p, CertificateIssuerDistinguishedName.class));
            case KmipTag.Standard.CERTIFICATE_ISSUER_ALTERNATIVE_NAME ->
                    builder.certificateIssuerAlternativeName(mapper.readValue(p, CertificateIssuerAlternativeName.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected CertificateIssuer build(CertificateIssuer.CertificateIssuerBuilder builder) {
        return builder.build();
    }

    @Override
    protected EncodingType getEncodingType() {
        return CertificateIssuer.encodingType;
    }
}
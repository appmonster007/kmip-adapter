package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common.structure;

import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.AbstractKmipStructureTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.common.CertificateSerialNumber;
import org.purpleBean.kmip.common.IssuerDistinguishedName;
import org.purpleBean.kmip.common.structure.X509CertificateIdentifier;

import java.io.IOException;
import java.nio.ByteBuffer;

public class X509CertificateIdentifierTtlvDeserializer extends AbstractKmipStructureTtlvDeserializer<X509CertificateIdentifier, X509CertificateIdentifier.X509CertificateIdentifierBuilder> {

    public X509CertificateIdentifierTtlvDeserializer() {
        super(X509CertificateIdentifier.kmipTag);
    }

    @Override
    protected X509CertificateIdentifier.X509CertificateIdentifierBuilder createBuilder() {
        return X509CertificateIdentifier.builder();
    }

    @Override
    protected void setValue(X509CertificateIdentifier.X509CertificateIdentifierBuilder builder, KmipTag.Value nodeTag, ByteBuffer p, TtlvMapper mapper) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.ISSUER_DISTINGUISHED_NAME ->
                    builder.issuerDistinguishedName(mapper.readValue(p, IssuerDistinguishedName.class));
            case KmipTag.Standard.CERTIFICATE_SERIAL_NUMBER ->
                    builder.certificateSerialNumber(mapper.readValue(p, CertificateSerialNumber.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected X509CertificateIdentifier build(X509CertificateIdentifier.X509CertificateIdentifierBuilder builder) {
        return builder.build();
    }

    @Override
    protected EncodingType getEncodingType() {
        return X509CertificateIdentifier.encodingType;
    }
}
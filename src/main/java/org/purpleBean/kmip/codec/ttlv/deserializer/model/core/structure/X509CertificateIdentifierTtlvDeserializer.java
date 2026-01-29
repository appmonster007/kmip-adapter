package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.structure;

import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.structure.X509CertificateIdentifier;
import org.purpleBean.kmip.model.core.type.CertificateSerialNumber;
import org.purpleBean.kmip.model.core.type.IssuerDistinguishedName;

import java.io.IOException;
import java.nio.ByteBuffer;

public class X509CertificateIdentifierTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<X509CertificateIdentifier, X509CertificateIdentifier.X509CertificateIdentifierBuilder> {

    public X509CertificateIdentifierTtlvDeserializer() {
        super(X509CertificateIdentifier.kmipTag, X509CertificateIdentifier.encodingType);
    }

    @Override
    protected X509CertificateIdentifier.X509CertificateIdentifierBuilder createBuilder() {
        return X509CertificateIdentifier.builder();
    }

    @Override
    protected void setValue(X509CertificateIdentifier.X509CertificateIdentifierBuilder builder, byte[] tagBytes, ByteBuffer p, TtlvMapper mapper) throws IOException {
        KmipTag.Value nodeTag = KmipTag.fromBytes(tagBytes);
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
}
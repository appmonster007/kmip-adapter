package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.CertificateIssuerDistinguishedName;

import java.io.IOException;
import java.nio.ByteBuffer;

public class CertificateIssuerDistinguishedNameTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<CertificateIssuerDistinguishedName, CertificateIssuerDistinguishedName.CertificateIssuerDistinguishedNameBuilder> {

    public CertificateIssuerDistinguishedNameTtlvDeserializer() {
        super(CertificateIssuerDistinguishedName.kmipTag, CertificateIssuerDistinguishedName.encodingType);
    }

    @Override
    protected CertificateIssuerDistinguishedName.CertificateIssuerDistinguishedNameBuilder createBuilder() {
        return CertificateIssuerDistinguishedName.builder();
    }

    @Override
    protected void setValue(CertificateIssuerDistinguishedName.CertificateIssuerDistinguishedNameBuilder builder, byte[] tagBytes, byte type, ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
        builder.value(mapper.readValue(byteBuffer, String.class));
    }

    @Override
    protected CertificateIssuerDistinguishedName build(CertificateIssuerDistinguishedName.CertificateIssuerDistinguishedNameBuilder builder) {
        return builder.build();
    }
}

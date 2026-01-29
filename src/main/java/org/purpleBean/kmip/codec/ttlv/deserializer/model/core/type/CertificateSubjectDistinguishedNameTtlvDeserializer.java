package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.CertificateSubjectDistinguishedName;

import java.io.IOException;
import java.nio.ByteBuffer;

public class CertificateSubjectDistinguishedNameTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<CertificateSubjectDistinguishedName, CertificateSubjectDistinguishedName.CertificateSubjectDistinguishedNameBuilder> {

    public CertificateSubjectDistinguishedNameTtlvDeserializer() {
        super(CertificateSubjectDistinguishedName.kmipTag, CertificateSubjectDistinguishedName.encodingType);
    }

    @Override
    protected CertificateSubjectDistinguishedName.CertificateSubjectDistinguishedNameBuilder createBuilder() {
        return CertificateSubjectDistinguishedName.builder();
    }

    @Override
    protected void setValue(CertificateSubjectDistinguishedName.CertificateSubjectDistinguishedNameBuilder builder, byte[] tagBytes, ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
        builder.value(mapper.readValue(byteBuffer, String.class));
    }

    @Override
    protected CertificateSubjectDistinguishedName build(CertificateSubjectDistinguishedName.CertificateSubjectDistinguishedNameBuilder builder) {
        return builder.build();
    }
}

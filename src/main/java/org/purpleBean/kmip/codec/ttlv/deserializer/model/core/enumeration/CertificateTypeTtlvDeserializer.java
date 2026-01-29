package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.enumeration.CertificateType;

import java.io.IOException;
import java.nio.ByteBuffer;

public class CertificateTypeTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<CertificateType, CertificateType.CertificateTypeBuilder> {

    public CertificateTypeTtlvDeserializer() {
        super(CertificateType.kmipTag, CertificateType.encodingType);
    }

    @Override
    protected CertificateType.CertificateTypeBuilder createBuilder() {
        return CertificateType.builder();
    }

    @Override
    protected void setValue(CertificateType.CertificateTypeBuilder builder, byte[] tagBytes, ByteBuffer p, TtlvMapper mapper) throws IOException {
        Integer value = mapper.readValue(p, Integer.class);
        builder.value(CertificateType.fromValue(value));
    }

    @Override
    protected CertificateType build(CertificateType.CertificateTypeBuilder builder) {
        return builder.build();
    }
}

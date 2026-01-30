package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.enumeration.CertificateRequestType;

import java.io.IOException;
import java.nio.ByteBuffer;

public class CertificateRequestTypeTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<CertificateRequestType, CertificateRequestType.CertificateRequestTypeBuilder> {

    public CertificateRequestTypeTtlvDeserializer() {
        super(CertificateRequestType.kmipTag, CertificateRequestType.encodingType);
    }

    @Override
    protected CertificateRequestType.CertificateRequestTypeBuilder createBuilder() {
        return CertificateRequestType.builder();
    }

    @Override
    protected void setValue(CertificateRequestType.CertificateRequestTypeBuilder builder, byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
        Integer value = mapper.readValue(p, Integer.class);
        builder.value(CertificateRequestType.fromValue(value));
    }

    @Override
    protected CertificateRequestType build(CertificateRequestType.CertificateRequestTypeBuilder builder) {
        return builder.build();
    }
}

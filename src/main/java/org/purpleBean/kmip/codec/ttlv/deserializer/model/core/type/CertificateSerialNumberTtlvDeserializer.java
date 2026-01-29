package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.CertificateSerialNumber;

import java.io.IOException;
import java.nio.ByteBuffer;

public class CertificateSerialNumberTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<CertificateSerialNumber, CertificateSerialNumber.CertificateSerialNumberBuilder> {

    public CertificateSerialNumberTtlvDeserializer() {
        super(CertificateSerialNumber.kmipTag, CertificateSerialNumber.encodingType);
    }

    @Override
    protected CertificateSerialNumber.CertificateSerialNumberBuilder createBuilder() {
        return CertificateSerialNumber.builder();
    }

    @Override
    protected void setValue(CertificateSerialNumber.CertificateSerialNumberBuilder builder, byte[] tagBytes, ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
        builder.value(mapper.readValue(byteBuffer, ByteBuffer.class));
    }

    @Override
    protected CertificateSerialNumber build(CertificateSerialNumber.CertificateSerialNumberBuilder builder) {
        return builder.build();
    }
}

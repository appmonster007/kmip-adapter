package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.common.CertificateSerialNumber;

import java.nio.ByteBuffer;

public class CertificateSerialNumberTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<CertificateSerialNumber, ByteBuffer> {

    public CertificateSerialNumberTtlvDeserializer() {
        super(CertificateSerialNumber.kmipTag, CertificateSerialNumber.encodingType, ByteBuffer.class, value -> CertificateSerialNumber.builder().value(value).build());
    }
}
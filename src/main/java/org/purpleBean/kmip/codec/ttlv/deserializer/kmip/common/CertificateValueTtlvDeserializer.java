package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.common.CertificateValue;

import java.nio.ByteBuffer;

public class CertificateValueTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<CertificateValue, ByteBuffer> {

    public CertificateValueTtlvDeserializer() {
        super(CertificateValue.kmipTag, CertificateValue.encodingType, ByteBuffer.class, value -> CertificateValue.builder().value(value).build());
    }
}
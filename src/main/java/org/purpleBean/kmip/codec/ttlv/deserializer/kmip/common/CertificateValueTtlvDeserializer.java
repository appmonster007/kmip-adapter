package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.deserializer.AbstractKmipTtlvDeserializer;
import org.purpleBean.kmip.common.CertificateValue;

import java.nio.ByteBuffer;

public class CertificateValueTtlvDeserializer extends AbstractKmipTtlvDeserializer<CertificateValue, ByteBuffer> {

    public CertificateValueTtlvDeserializer() {
        super(CertificateValue.kmipTag, CertificateValue.encodingType, ByteBuffer.class, value -> CertificateValue.builder().value(value).build());
    }
}
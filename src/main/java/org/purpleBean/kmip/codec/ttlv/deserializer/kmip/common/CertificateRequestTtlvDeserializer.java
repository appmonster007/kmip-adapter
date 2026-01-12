package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.deserializer.AbstractKmipTtlvDeserializer;
import org.purpleBean.kmip.common.CertificateRequest;

import java.nio.ByteBuffer;

public class CertificateRequestTtlvDeserializer extends AbstractKmipTtlvDeserializer<CertificateRequest, ByteBuffer> {

    public CertificateRequestTtlvDeserializer() {
        super(CertificateRequest.kmipTag, CertificateRequest.encodingType, ByteBuffer.class, value -> CertificateRequest.builder().value(value).build());
    }
}
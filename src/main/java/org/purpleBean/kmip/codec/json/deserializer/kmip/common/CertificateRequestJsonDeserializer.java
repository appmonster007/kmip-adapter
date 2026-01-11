package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import org.purpleBean.kmip.codec.json.deserializer.AbstractKmipJsonDeserializer;
import org.purpleBean.kmip.common.CertificateRequest;

import java.nio.ByteBuffer;

public class CertificateRequestJsonDeserializer extends AbstractKmipJsonDeserializer<CertificateRequest, ByteBuffer> {

    public CertificateRequestJsonDeserializer() {
        super(CertificateRequest.kmipTag, CertificateRequest.encodingType, ByteBuffer.class, value -> CertificateRequest.builder().value(value).build());
    }
}
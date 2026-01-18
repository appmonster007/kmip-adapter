package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.model.core.type.CertificateRequest;

import java.nio.ByteBuffer;

public class CertificateRequestTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<CertificateRequest, ByteBuffer> {

    public CertificateRequestTtlvDeserializer() {
        super(CertificateRequest.kmipTag, CertificateRequest.encodingType, ByteBuffer.class, value -> CertificateRequest.builder().value(value).build());
    }
}
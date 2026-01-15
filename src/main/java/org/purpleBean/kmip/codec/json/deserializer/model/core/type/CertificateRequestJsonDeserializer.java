package org.purpleBean.kmip.codec.json.deserializer.model.core.type;

import org.purpleBean.kmip.codec.json.deserializer.kmip.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.type.CertificateRequest;

import java.nio.ByteBuffer;

public class CertificateRequestJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<CertificateRequest, ByteBuffer> {

    public CertificateRequestJsonDeserializer() {
        super(CertificateRequest.kmipTag, CertificateRequest.encodingType, ByteBuffer.class, value -> CertificateRequest.builder().value(value).build());
    }
}
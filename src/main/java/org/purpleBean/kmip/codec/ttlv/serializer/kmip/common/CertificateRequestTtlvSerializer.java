package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.AbstractKmipTtlvSerializer;
import org.purpleBean.kmip.common.CertificateRequest;

import java.nio.ByteBuffer;

public class CertificateRequestTtlvSerializer extends AbstractKmipTtlvSerializer<CertificateRequest, ByteBuffer> {

    public CertificateRequestTtlvSerializer() {
        super(CertificateRequest::getValue);
    }
}
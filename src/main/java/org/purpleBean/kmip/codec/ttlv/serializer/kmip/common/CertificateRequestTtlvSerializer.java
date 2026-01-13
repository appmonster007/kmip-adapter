package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.common.CertificateRequest;

import java.nio.ByteBuffer;

public class CertificateRequestTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<CertificateRequest, ByteBuffer> {

    public CertificateRequestTtlvSerializer() {
        super(CertificateRequest::getValue);
    }
}
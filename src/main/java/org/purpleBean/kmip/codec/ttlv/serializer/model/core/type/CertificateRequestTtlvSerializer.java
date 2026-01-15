package org.purpleBean.kmip.codec.ttlv.serializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.model.core.type.CertificateRequest;

import java.nio.ByteBuffer;

public class CertificateRequestTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<CertificateRequest, ByteBuffer> {

    public CertificateRequestTtlvSerializer() {
        super(CertificateRequest::getValue);
    }
}
package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.AbstractKmipJsonSerializer;
import org.purpleBean.kmip.common.CertificateRequest;

import java.nio.ByteBuffer;

public class CertificateRequestJsonSerializer extends AbstractKmipJsonSerializer<CertificateRequest, ByteBuffer> {

    public CertificateRequestJsonSerializer() {
        super(CertificateRequest::getValue);
    }
}
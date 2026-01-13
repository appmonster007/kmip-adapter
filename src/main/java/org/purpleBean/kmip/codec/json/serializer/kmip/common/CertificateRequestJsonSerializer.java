package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.CertificateRequest;

import java.nio.ByteBuffer;

public class CertificateRequestJsonSerializer extends AbstractKmipDataTypeJsonSerializer<CertificateRequest, ByteBuffer> {

    public CertificateRequestJsonSerializer() {
        super(CertificateRequest::getValue);
    }
}
package org.purpleBean.kmip.codec.json.serializer.model.core.type;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.model.core.type.CertificateRequest;

import java.nio.ByteBuffer;

public class CertificateRequestJsonSerializer extends AbstractKmipDataTypeJsonSerializer<CertificateRequest, ByteBuffer> {

    public CertificateRequestJsonSerializer() {
        super(CertificateRequest::getValue);
    }
}
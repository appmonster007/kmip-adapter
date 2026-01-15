package org.purpleBean.kmip.codec.xml.serializer.model.core.type;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.model.core.type.CertificateRequest;

import java.nio.ByteBuffer;

public class CertificateRequestXmlSerializer extends AbstractKmipDataTypeXmlSerializer<CertificateRequest, ByteBuffer> {

    public CertificateRequestXmlSerializer() {
        super(CertificateRequest::getValue);
    }
}
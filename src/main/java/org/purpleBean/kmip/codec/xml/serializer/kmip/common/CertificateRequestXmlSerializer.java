package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;
import org.purpleBean.kmip.common.CertificateRequest;

import java.nio.ByteBuffer;

public class CertificateRequestXmlSerializer extends AbstractKmipXmlSerializer<CertificateRequest, ByteBuffer> {

    public CertificateRequestXmlSerializer() {
        super(CertificateRequest::getValue);
    }
}
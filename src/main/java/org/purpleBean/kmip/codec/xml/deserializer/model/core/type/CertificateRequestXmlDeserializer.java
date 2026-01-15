package org.purpleBean.kmip.codec.xml.deserializer.model.core.type;

import org.purpleBean.kmip.codec.xml.deserializer.kmip.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.type.CertificateRequest;

import java.nio.ByteBuffer;

public class CertificateRequestXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<CertificateRequest, ByteBuffer> {

    public CertificateRequestXmlDeserializer() {
        super(CertificateRequest.kmipTag, CertificateRequest.encodingType, ByteBuffer.class, value -> CertificateRequest.builder().value(value).build());
    }
}
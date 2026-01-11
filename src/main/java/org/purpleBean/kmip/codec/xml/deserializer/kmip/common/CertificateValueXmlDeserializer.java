package org.purpleBean.kmip.codec.xml.deserializer.kmip.common;

import org.purpleBean.kmip.codec.xml.deserializer.AbstractKmipXmlDeserializer;
import org.purpleBean.kmip.common.CertificateValue;

import java.nio.ByteBuffer;

public class CertificateValueXmlDeserializer extends AbstractKmipXmlDeserializer<CertificateValue, ByteBuffer> {

    public CertificateValueXmlDeserializer() {
        super(CertificateValue.kmipTag, CertificateValue.encodingType, ByteBuffer.class, value -> CertificateValue.builder().value(value).build());
    }
}
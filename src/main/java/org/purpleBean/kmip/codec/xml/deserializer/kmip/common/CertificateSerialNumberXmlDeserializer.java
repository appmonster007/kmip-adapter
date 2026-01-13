package org.purpleBean.kmip.codec.xml.deserializer.kmip.common;

import org.purpleBean.kmip.codec.xml.deserializer.kmip.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.common.CertificateSerialNumber;

import java.nio.ByteBuffer;

public class CertificateSerialNumberXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<CertificateSerialNumber, ByteBuffer> {

    public CertificateSerialNumberXmlDeserializer() {
        super(CertificateSerialNumber.kmipTag, CertificateSerialNumber.encodingType, ByteBuffer.class, value -> CertificateSerialNumber.builder().value(value).build());
    }
}
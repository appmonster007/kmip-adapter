package org.purpleBean.kmip.codec.xml.deserializer.model.core.type;

import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.type.CertificateSerialNumber;

import java.nio.ByteBuffer;

public class CertificateSerialNumberXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<CertificateSerialNumber, ByteBuffer> {

    public CertificateSerialNumberXmlDeserializer() {
        super(CertificateSerialNumber.kmipTag, CertificateSerialNumber.encodingType, ByteBuffer.class, value -> CertificateSerialNumber.builder().value(value).build());
    }
}
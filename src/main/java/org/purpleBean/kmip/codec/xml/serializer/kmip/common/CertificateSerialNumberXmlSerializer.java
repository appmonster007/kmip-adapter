package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.CertificateSerialNumber;

import java.nio.ByteBuffer;

public class CertificateSerialNumberXmlSerializer extends AbstractKmipDataTypeXmlSerializer<CertificateSerialNumber, ByteBuffer> {

    public CertificateSerialNumberXmlSerializer() {
        super(CertificateSerialNumber::getValue);
    }
}
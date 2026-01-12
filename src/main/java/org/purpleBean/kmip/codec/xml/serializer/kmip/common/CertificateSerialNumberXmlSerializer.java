package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;
import org.purpleBean.kmip.common.CertificateSerialNumber;

import java.nio.ByteBuffer;

public class CertificateSerialNumberXmlSerializer extends AbstractKmipXmlSerializer<CertificateSerialNumber, ByteBuffer> {

    public CertificateSerialNumberXmlSerializer() {
        super(CertificateSerialNumber::getValue);
    }
}
package org.purpleBean.kmip.codec.xml.serializer.model.core.type;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.model.core.type.CertificateSerialNumber;

import java.nio.ByteBuffer;

public class CertificateSerialNumberXmlSerializer extends AbstractKmipDataTypeXmlSerializer<CertificateSerialNumber, ByteBuffer> {

    public CertificateSerialNumberXmlSerializer() {
        super(CertificateSerialNumber::getValue);
    }
}
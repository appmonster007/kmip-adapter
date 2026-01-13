package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.CertificateValue;

import java.nio.ByteBuffer;

public class CertificateValueXmlSerializer extends AbstractKmipDataTypeXmlSerializer<CertificateValue, ByteBuffer> {

    public CertificateValueXmlSerializer() {
        super(CertificateValue::getValue);
    }
}
package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.common.CertificateValue;
import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;
import java.nio.ByteBuffer;

public class CertificateValueXmlSerializer extends AbstractKmipXmlSerializer<CertificateValue, ByteBuffer> {

    public CertificateValueXmlSerializer() {
        super(CertificateValue::getValue);
    }
}
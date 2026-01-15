package org.purpleBean.kmip.codec.xml.serializer.model.core.type;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.model.core.type.CertificateValue;

import java.nio.ByteBuffer;

public class CertificateValueXmlSerializer extends AbstractKmipDataTypeXmlSerializer<CertificateValue, ByteBuffer> {

    public CertificateValueXmlSerializer() {
        super(CertificateValue::getValue);
    }
}
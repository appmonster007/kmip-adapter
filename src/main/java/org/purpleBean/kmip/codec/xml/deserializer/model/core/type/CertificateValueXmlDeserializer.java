package org.purpleBean.kmip.codec.xml.deserializer.model.core.type;

import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.type.CertificateValue;

import java.nio.ByteBuffer;

public class CertificateValueXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<CertificateValue, ByteBuffer> {

    public CertificateValueXmlDeserializer() {
        super(CertificateValue.kmipTag, CertificateValue.encodingType, ByteBuffer.class, value -> CertificateValue.builder().value(value).build());
    }
}
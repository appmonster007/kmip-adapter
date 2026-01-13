package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import org.purpleBean.kmip.codec.json.deserializer.kmip.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.CertificateValue;

import java.nio.ByteBuffer;

public class CertificateValueJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<CertificateValue, ByteBuffer> {

    public CertificateValueJsonDeserializer() {
        super(CertificateValue.kmipTag, CertificateValue.encodingType, ByteBuffer.class, value -> CertificateValue.builder().value(value).build());
    }
}
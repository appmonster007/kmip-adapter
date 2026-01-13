package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import org.purpleBean.kmip.codec.json.deserializer.kmip.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.CertificateSerialNumber;

import java.nio.ByteBuffer;

public class CertificateSerialNumberJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<CertificateSerialNumber, ByteBuffer> {

    public CertificateSerialNumberJsonDeserializer() {
        super(CertificateSerialNumber.kmipTag, CertificateSerialNumber.encodingType, ByteBuffer.class, value -> CertificateSerialNumber.builder().value(value).build());
    }
}
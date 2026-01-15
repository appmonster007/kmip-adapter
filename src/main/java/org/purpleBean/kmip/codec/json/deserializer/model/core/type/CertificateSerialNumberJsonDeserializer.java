package org.purpleBean.kmip.codec.json.deserializer.model.core.type;

import org.purpleBean.kmip.codec.json.deserializer.kmip.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.type.CertificateSerialNumber;

import java.nio.ByteBuffer;

public class CertificateSerialNumberJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<CertificateSerialNumber, ByteBuffer> {

    public CertificateSerialNumberJsonDeserializer() {
        super(CertificateSerialNumber.kmipTag, CertificateSerialNumber.encodingType, ByteBuffer.class, value -> CertificateSerialNumber.builder().value(value).build());
    }
}
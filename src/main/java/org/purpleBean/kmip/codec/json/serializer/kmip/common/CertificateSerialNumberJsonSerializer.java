package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.CertificateSerialNumber;

import java.nio.ByteBuffer;

public class CertificateSerialNumberJsonSerializer extends AbstractKmipDataTypeJsonSerializer<CertificateSerialNumber, ByteBuffer> {

    public CertificateSerialNumberJsonSerializer() {
        super(CertificateSerialNumber::getValue);
    }
}
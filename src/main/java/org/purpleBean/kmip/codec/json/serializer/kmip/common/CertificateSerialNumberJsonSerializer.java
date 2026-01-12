package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.AbstractKmipJsonSerializer;
import org.purpleBean.kmip.common.CertificateSerialNumber;

import java.nio.ByteBuffer;

public class CertificateSerialNumberJsonSerializer extends AbstractKmipJsonSerializer<CertificateSerialNumber, ByteBuffer> {

    public CertificateSerialNumberJsonSerializer() {
        super(CertificateSerialNumber::getValue);
    }
}
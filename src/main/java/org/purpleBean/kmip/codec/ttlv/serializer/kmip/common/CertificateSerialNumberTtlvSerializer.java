package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.AbstractKmipTtlvSerializer;
import org.purpleBean.kmip.common.CertificateSerialNumber;

import java.nio.ByteBuffer;

public class CertificateSerialNumberTtlvSerializer extends AbstractKmipTtlvSerializer<CertificateSerialNumber, ByteBuffer> {

    public CertificateSerialNumberTtlvSerializer() {
        super(CertificateSerialNumber::getValue);
    }
}
package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.common.CertificateSerialNumber;

import java.nio.ByteBuffer;

public class CertificateSerialNumberTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<CertificateSerialNumber, ByteBuffer> {

    public CertificateSerialNumberTtlvSerializer() {
        super(CertificateSerialNumber::getValue);
    }
}
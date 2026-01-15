package org.purpleBean.kmip.codec.ttlv.serializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.model.core.type.CertificateSerialNumber;

import java.nio.ByteBuffer;

public class CertificateSerialNumberTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<CertificateSerialNumber, ByteBuffer> {

    public CertificateSerialNumberTtlvSerializer() {
        super(CertificateSerialNumber::getValue);
    }
}
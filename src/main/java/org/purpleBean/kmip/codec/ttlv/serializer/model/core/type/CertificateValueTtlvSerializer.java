package org.purpleBean.kmip.codec.ttlv.serializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.model.core.type.CertificateValue;

import java.nio.ByteBuffer;

public class CertificateValueTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<CertificateValue, ByteBuffer> {

    public CertificateValueTtlvSerializer() {
        super(CertificateValue::getValue);
    }
}
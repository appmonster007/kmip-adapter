package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.AbstractKmipJsonSerializer;
import org.purpleBean.kmip.common.CertificateValue;

import java.nio.ByteBuffer;

public class CertificateValueJsonSerializer extends AbstractKmipJsonSerializer<CertificateValue, ByteBuffer> {

    public CertificateValueJsonSerializer() {
        super(CertificateValue::getValue);
    }
}
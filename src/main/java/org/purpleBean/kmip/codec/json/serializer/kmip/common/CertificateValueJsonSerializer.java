package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.CertificateValue;

import java.nio.ByteBuffer;

public class CertificateValueJsonSerializer extends AbstractKmipDataTypeJsonSerializer<CertificateValue, ByteBuffer> {

    public CertificateValueJsonSerializer() {
        super(CertificateValue::getValue);
    }
}
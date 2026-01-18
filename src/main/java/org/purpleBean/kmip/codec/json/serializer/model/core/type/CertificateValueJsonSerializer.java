package org.purpleBean.kmip.codec.json.serializer.model.core.type;

import org.purpleBean.kmip.codec.json.serializer.api.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.model.core.type.CertificateValue;

import java.nio.ByteBuffer;

public class CertificateValueJsonSerializer extends AbstractKmipDataTypeJsonSerializer<CertificateValue, ByteBuffer> {

    public CertificateValueJsonSerializer() {
        super(CertificateValue::getValue);
    }
}
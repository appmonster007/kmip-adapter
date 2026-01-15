package org.purpleBean.kmip.codec.json.serializer.model.core.type;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.model.core.type.CertificateSerialNumber;

import java.nio.ByteBuffer;

public class CertificateSerialNumberJsonSerializer extends AbstractKmipDataTypeJsonSerializer<CertificateSerialNumber, ByteBuffer> {

    public CertificateSerialNumberJsonSerializer() {
        super(CertificateSerialNumber::getValue);
    }
}
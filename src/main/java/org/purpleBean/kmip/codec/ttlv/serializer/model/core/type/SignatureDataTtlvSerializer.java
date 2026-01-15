package org.purpleBean.kmip.codec.ttlv.serializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.model.core.type.SignatureData;

import java.nio.ByteBuffer;

public class SignatureDataTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<SignatureData, ByteBuffer> {

    public SignatureDataTtlvSerializer() {
        super(SignatureData::getValue);
    }
}
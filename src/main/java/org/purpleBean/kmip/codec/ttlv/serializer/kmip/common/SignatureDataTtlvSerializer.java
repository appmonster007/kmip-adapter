package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.common.SignatureData;

import java.nio.ByteBuffer;

public class SignatureDataTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<SignatureData, ByteBuffer> {

    public SignatureDataTtlvSerializer() {
        super(SignatureData::getValue);
    }
}
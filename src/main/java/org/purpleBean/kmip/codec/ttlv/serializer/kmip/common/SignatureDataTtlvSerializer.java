package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.AbstractKmipTtlvSerializer;
import org.purpleBean.kmip.common.SignatureData;

import java.nio.ByteBuffer;

public class SignatureDataTtlvSerializer extends AbstractKmipTtlvSerializer<SignatureData, ByteBuffer> {

    public SignatureDataTtlvSerializer() {
        super(SignatureData::getValue);
    }
}
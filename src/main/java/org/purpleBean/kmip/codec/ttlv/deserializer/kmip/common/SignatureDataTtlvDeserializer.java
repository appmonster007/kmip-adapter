package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.deserializer.AbstractKmipTtlvDeserializer;
import org.purpleBean.kmip.common.SignatureData;

import java.nio.ByteBuffer;

public class SignatureDataTtlvDeserializer extends AbstractKmipTtlvDeserializer<SignatureData, ByteBuffer> {

    public SignatureDataTtlvDeserializer() {
        super(SignatureData.kmipTag, SignatureData.encodingType, ByteBuffer.class, value -> SignatureData.builder().value(value).build());
    }
}
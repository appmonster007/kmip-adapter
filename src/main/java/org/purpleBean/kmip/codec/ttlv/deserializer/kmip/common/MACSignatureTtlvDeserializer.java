package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.deserializer.AbstractKmipTtlvDeserializer;
import org.purpleBean.kmip.common.MACSignature;

import java.nio.ByteBuffer;

public class MACSignatureTtlvDeserializer extends AbstractKmipTtlvDeserializer<MACSignature, ByteBuffer> {

    public MACSignatureTtlvDeserializer() {
        super(MACSignature.kmipTag, MACSignature.encodingType, ByteBuffer.class, value -> MACSignature.builder().value(value).build());
    }
}
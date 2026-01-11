package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import org.purpleBean.kmip.codec.json.deserializer.AbstractKmipJsonDeserializer;
import org.purpleBean.kmip.common.MACSignature;

import java.nio.ByteBuffer;

public class MACSignatureJsonDeserializer extends AbstractKmipJsonDeserializer<MACSignature, ByteBuffer> {

    public MACSignatureJsonDeserializer() {
        super(MACSignature.kmipTag, MACSignature.encodingType, ByteBuffer.class, value -> MACSignature.builder().value(value).build());
    }
}
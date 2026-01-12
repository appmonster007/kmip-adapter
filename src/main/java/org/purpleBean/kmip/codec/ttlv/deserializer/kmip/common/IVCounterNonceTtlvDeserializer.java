package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.deserializer.AbstractKmipTtlvDeserializer;
import org.purpleBean.kmip.common.IVCounterNonce;

import java.nio.ByteBuffer;

public class IVCounterNonceTtlvDeserializer extends AbstractKmipTtlvDeserializer<IVCounterNonce, ByteBuffer> {

    public IVCounterNonceTtlvDeserializer() {
        super(IVCounterNonce.kmipTag, IVCounterNonce.encodingType, ByteBuffer.class, value -> IVCounterNonce.builder().value(value).build());
    }
}
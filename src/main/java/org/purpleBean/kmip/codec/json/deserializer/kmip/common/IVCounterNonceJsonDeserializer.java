package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import org.purpleBean.kmip.codec.json.deserializer.AbstractKmipJsonDeserializer;
import org.purpleBean.kmip.common.IVCounterNonce;

import java.nio.ByteBuffer;

public class IVCounterNonceJsonDeserializer extends AbstractKmipJsonDeserializer<IVCounterNonce, ByteBuffer> {

    public IVCounterNonceJsonDeserializer() {
        super(IVCounterNonce.kmipTag, IVCounterNonce.encodingType, ByteBuffer.class, value -> IVCounterNonce.builder().value(value).build());
    }
}
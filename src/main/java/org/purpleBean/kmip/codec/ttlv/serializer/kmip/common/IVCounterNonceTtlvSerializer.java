package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.AbstractKmipTtlvSerializer;
import org.purpleBean.kmip.common.IVCounterNonce;

import java.nio.ByteBuffer;

public class IVCounterNonceTtlvSerializer extends AbstractKmipTtlvSerializer<IVCounterNonce, ByteBuffer> {

    public IVCounterNonceTtlvSerializer() {
        super(IVCounterNonce::getValue);
    }
}
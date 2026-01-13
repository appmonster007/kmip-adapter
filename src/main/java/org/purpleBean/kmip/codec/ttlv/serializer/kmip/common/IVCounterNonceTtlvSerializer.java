package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.common.IVCounterNonce;

import java.nio.ByteBuffer;

public class IVCounterNonceTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<IVCounterNonce, ByteBuffer> {

    public IVCounterNonceTtlvSerializer() {
        super(IVCounterNonce::getValue);
    }
}
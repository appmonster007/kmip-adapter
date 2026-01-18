package org.purpleBean.kmip.codec.ttlv.serializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.serializer.api.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.model.core.type.IVCounterNonce;

import java.nio.ByteBuffer;

public class IVCounterNonceTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<IVCounterNonce, ByteBuffer> {

    public IVCounterNonceTtlvSerializer() {
        super(IVCounterNonce::getValue);
    }
}
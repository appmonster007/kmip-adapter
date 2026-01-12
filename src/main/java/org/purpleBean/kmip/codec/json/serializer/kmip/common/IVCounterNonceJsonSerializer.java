package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.AbstractKmipJsonSerializer;
import org.purpleBean.kmip.common.IVCounterNonce;

import java.nio.ByteBuffer;

public class IVCounterNonceJsonSerializer extends AbstractKmipJsonSerializer<IVCounterNonce, ByteBuffer> {

    public IVCounterNonceJsonSerializer() {
        super(IVCounterNonce::getValue);
    }
}
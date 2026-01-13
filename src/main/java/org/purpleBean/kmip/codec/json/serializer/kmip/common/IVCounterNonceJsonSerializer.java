package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.IVCounterNonce;

import java.nio.ByteBuffer;

public class IVCounterNonceJsonSerializer extends AbstractKmipDataTypeJsonSerializer<IVCounterNonce, ByteBuffer> {

    public IVCounterNonceJsonSerializer() {
        super(IVCounterNonce::getValue);
    }
}
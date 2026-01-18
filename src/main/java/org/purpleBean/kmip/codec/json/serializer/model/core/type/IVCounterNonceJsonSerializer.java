package org.purpleBean.kmip.codec.json.serializer.model.core.type;

import org.purpleBean.kmip.codec.json.serializer.api.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.model.core.type.IVCounterNonce;

import java.nio.ByteBuffer;

public class IVCounterNonceJsonSerializer extends AbstractKmipDataTypeJsonSerializer<IVCounterNonce, ByteBuffer> {

    public IVCounterNonceJsonSerializer() {
        super(IVCounterNonce::getValue);
    }
}
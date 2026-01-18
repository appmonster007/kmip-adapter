package org.purpleBean.kmip.codec.json.deserializer.model.core.type;

import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.type.IVCounterNonce;

import java.nio.ByteBuffer;

public class IVCounterNonceJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<IVCounterNonce, ByteBuffer> {

    public IVCounterNonceJsonDeserializer() {
        super(IVCounterNonce.kmipTag, IVCounterNonce.encodingType, ByteBuffer.class, value -> IVCounterNonce.builder().value(value).build());
    }
}
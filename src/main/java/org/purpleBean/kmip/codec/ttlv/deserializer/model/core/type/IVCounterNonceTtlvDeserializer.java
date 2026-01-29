package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.IVCounterNonce;

import java.io.IOException;
import java.nio.ByteBuffer;

public class IVCounterNonceTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<IVCounterNonce, IVCounterNonce.IVCounterNonceBuilder> {

    public IVCounterNonceTtlvDeserializer() {
        super(IVCounterNonce.kmipTag, IVCounterNonce.encodingType);
    }

    @Override
    protected IVCounterNonce.IVCounterNonceBuilder createBuilder() {
        return IVCounterNonce.builder();
    }

    @Override
    protected void setValue(IVCounterNonce.IVCounterNonceBuilder builder, byte[] tagBytes, ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
        builder.value(mapper.readValue(byteBuffer, ByteBuffer.class));
    }

    @Override
    protected IVCounterNonce build(IVCounterNonce.IVCounterNonceBuilder builder) {
        return builder.build();
    }
}

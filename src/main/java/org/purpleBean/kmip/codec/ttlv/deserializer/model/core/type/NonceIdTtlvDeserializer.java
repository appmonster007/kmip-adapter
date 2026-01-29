package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.NonceId;

import java.io.IOException;
import java.nio.ByteBuffer;

public class NonceIdTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<NonceId, NonceId.NonceIdBuilder> {

    public NonceIdTtlvDeserializer() {
        super(NonceId.kmipTag, NonceId.encodingType);
    }

    @Override
    protected NonceId.NonceIdBuilder createBuilder() {
        return NonceId.builder();
    }

    @Override
    protected void setValue(NonceId.NonceIdBuilder builder, byte[] tagBytes, byte type, ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
        builder.value(mapper.readValue(byteBuffer, ByteBuffer.class));
    }

    @Override
    protected NonceId build(NonceId.NonceIdBuilder builder) {
        return builder.build();
    }
}

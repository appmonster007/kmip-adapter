package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.Key;

import java.io.IOException;
import java.nio.ByteBuffer;

public class KeyTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<Key, Key.KeyBuilder> {

    public KeyTtlvDeserializer() {
        super(Key.kmipTag, Key.encodingType);
    }

    @Override
    protected Key.KeyBuilder createBuilder() {
        return Key.builder();
    }

    @Override
    protected void setValue(Key.KeyBuilder builder, byte[] tag, byte type, ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
        builder.value(mapper.readValue(byteBuffer, ByteBuffer.class));
    }

    @Override
    protected Key build(Key.KeyBuilder builder) {
        return builder.build();
    }
}

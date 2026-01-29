package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.enumeration.KeyWrapType;

import java.io.IOException;
import java.nio.ByteBuffer;

public class KeyWrapTypeTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<KeyWrapType, KeyWrapType.KeyWrapTypeBuilder> {

    public KeyWrapTypeTtlvDeserializer() {
        super(KeyWrapType.kmipTag, KeyWrapType.encodingType);
    }

    @Override
    protected KeyWrapType.KeyWrapTypeBuilder createBuilder() {
        return KeyWrapType.builder();
    }

    @Override
    protected void setValue(KeyWrapType.KeyWrapTypeBuilder builder, byte[] tagBytes, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
        Integer value = mapper.readValue(p, Integer.class);
        builder.value(KeyWrapType.fromValue(value));
    }

    @Override
    protected KeyWrapType build(KeyWrapType.KeyWrapTypeBuilder builder) {
        return builder.build();
    }
}

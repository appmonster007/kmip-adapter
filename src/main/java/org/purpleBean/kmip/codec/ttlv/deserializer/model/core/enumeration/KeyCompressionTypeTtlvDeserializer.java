package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.enumeration.KeyCompressionType;

import java.io.IOException;
import java.nio.ByteBuffer;

public class KeyCompressionTypeTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<KeyCompressionType, KeyCompressionType.KeyCompressionTypeBuilder> {

    public KeyCompressionTypeTtlvDeserializer() {
        super(KeyCompressionType.kmipTag, KeyCompressionType.encodingType);
    }

    @Override
    protected KeyCompressionType.KeyCompressionTypeBuilder createBuilder() {
        return KeyCompressionType.builder();
    }

    @Override
    protected void setValue(KeyCompressionType.KeyCompressionTypeBuilder builder, byte[] tagBytes, ByteBuffer p, TtlvMapper mapper) throws IOException {
        Integer value = mapper.readValue(p, Integer.class);
        builder.value(KeyCompressionType.fromValue(value));
    }

    @Override
    protected KeyCompressionType build(KeyCompressionType.KeyCompressionTypeBuilder builder) {
        return builder.build();
    }
}

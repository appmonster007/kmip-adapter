package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.enumeration.KeyValueLocationType;

import java.io.IOException;
import java.nio.ByteBuffer;

public class KeyValueLocationTypeTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<KeyValueLocationType, KeyValueLocationType.KeyValueLocationTypeBuilder> {

    public KeyValueLocationTypeTtlvDeserializer() {
        super(KeyValueLocationType.kmipTag, KeyValueLocationType.encodingType);
    }

    @Override
    protected KeyValueLocationType.KeyValueLocationTypeBuilder createBuilder() {
        return KeyValueLocationType.builder();
    }

    @Override
    protected void setValue(KeyValueLocationType.KeyValueLocationTypeBuilder builder, byte[] tagBytes, ByteBuffer p, TtlvMapper mapper) throws IOException {
        Integer value = mapper.readValue(p, Integer.class);
        builder.value(KeyValueLocationType.fromValue(value));
    }

    @Override
    protected KeyValueLocationType build(KeyValueLocationType.KeyValueLocationTypeBuilder builder) {
        return builder.build();
    }
}

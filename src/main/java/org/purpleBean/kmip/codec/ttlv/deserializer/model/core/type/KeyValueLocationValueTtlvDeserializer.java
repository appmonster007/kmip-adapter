package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.KeyValueLocationValue;

import java.io.IOException;
import java.nio.ByteBuffer;

public class KeyValueLocationValueTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<KeyValueLocationValue, KeyValueLocationValue.KeyValueLocationValueBuilder> {

    public KeyValueLocationValueTtlvDeserializer() {
        super(KeyValueLocationValue.kmipTag, KeyValueLocationValue.encodingType);
    }

    @Override
    protected KeyValueLocationValue.KeyValueLocationValueBuilder createBuilder() {
        return KeyValueLocationValue.builder();
    }

    @Override
    protected void setValue(KeyValueLocationValue.KeyValueLocationValueBuilder builder, byte[] tagBytes, byte type, ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
        builder.value(mapper.readValue(byteBuffer, String.class));
    }

    @Override
    protected KeyValueLocationValue build(KeyValueLocationValue.KeyValueLocationValueBuilder builder) {
        return builder.build();
    }
}

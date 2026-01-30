package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.KeyValuePresent;

import java.io.IOException;
import java.nio.ByteBuffer;

public class KeyValuePresentTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<KeyValuePresent, KeyValuePresent.KeyValuePresentBuilder> {

    public KeyValuePresentTtlvDeserializer() {
        super(KeyValuePresent.kmipTag, KeyValuePresent.encodingType);
    }

    @Override
    protected KeyValuePresent.KeyValuePresentBuilder createBuilder() {
        return KeyValuePresent.builder();
    }

    @Override
    protected void setValue(KeyValuePresent.KeyValuePresentBuilder builder, byte[] tag, byte type, ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
        builder.value(mapper.readValue(byteBuffer, Boolean.class));
    }

    @Override
    protected KeyValuePresent build(KeyValuePresent.KeyValuePresentBuilder builder) {
        return builder.build();
    }
}

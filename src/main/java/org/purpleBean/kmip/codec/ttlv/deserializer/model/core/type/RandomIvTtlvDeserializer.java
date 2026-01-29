package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.RandomIv;

import java.io.IOException;
import java.nio.ByteBuffer;

public class RandomIvTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<RandomIv, RandomIv.RandomIvBuilder> {

    public RandomIvTtlvDeserializer() {
        super(RandomIv.kmipTag, RandomIv.encodingType);
    }

    @Override
    protected RandomIv.RandomIvBuilder createBuilder() {
        return RandomIv.builder();
    }

    @Override
    protected void setValue(RandomIv.RandomIvBuilder builder, byte[] tagBytes, byte type, ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
        builder.value(mapper.readValue(byteBuffer, Boolean.class));
    }

    @Override
    protected RandomIv build(RandomIv.RandomIvBuilder builder) {
        return builder.build();
    }
}

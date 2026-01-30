package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.CounterLength;

import java.io.IOException;
import java.nio.ByteBuffer;

public class CounterLengthTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<CounterLength, CounterLength.CounterLengthBuilder> {

    public CounterLengthTtlvDeserializer() {
        super(CounterLength.kmipTag, CounterLength.encodingType);
    }

    @Override
    protected CounterLength.CounterLengthBuilder createBuilder() {
        return CounterLength.builder();
    }

    @Override
    protected void setValue(CounterLength.CounterLengthBuilder builder, byte[] tag, byte type, ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
        builder.value(mapper.readValue(byteBuffer, Integer.class));
    }

    @Override
    protected CounterLength build(CounterLength.CounterLengthBuilder builder) {
        return builder.build();
    }
}

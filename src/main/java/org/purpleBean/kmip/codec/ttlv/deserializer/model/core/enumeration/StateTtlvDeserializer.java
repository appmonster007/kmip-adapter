package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.enumeration.State;

import java.io.IOException;
import java.nio.ByteBuffer;

public class StateTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<State, State.StateBuilder> {

    public StateTtlvDeserializer() {
        super(State.kmipTag, State.encodingType);
    }

    @Override
    protected State.StateBuilder createBuilder() {
        return State.builder();
    }

    @Override
    protected void setValue(State.StateBuilder builder, byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
        Integer value = mapper.readValue(p, Integer.class);
        builder.value(State.fromValue(value));
    }

    @Override
    protected State build(State.StateBuilder builder) {
        return builder.build();
    }
}

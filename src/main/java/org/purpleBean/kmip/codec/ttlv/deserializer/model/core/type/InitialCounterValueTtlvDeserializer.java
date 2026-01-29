package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.InitialCounterValue;

import java.io.IOException;
import java.nio.ByteBuffer;

public class InitialCounterValueTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<InitialCounterValue, InitialCounterValue.InitialCounterValueBuilder> {

    public InitialCounterValueTtlvDeserializer() {
        super(InitialCounterValue.kmipTag, InitialCounterValue.encodingType);
    }

    @Override
    protected InitialCounterValue.InitialCounterValueBuilder createBuilder() {
        return InitialCounterValue.builder();
    }

    @Override
    protected void setValue(InitialCounterValue.InitialCounterValueBuilder builder, byte[] tagBytes, byte type, ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
        builder.value(mapper.readValue(byteBuffer, Integer.class));
    }

    @Override
    protected InitialCounterValue build(InitialCounterValue.InitialCounterValueBuilder builder) {
        return builder.build();
    }
}

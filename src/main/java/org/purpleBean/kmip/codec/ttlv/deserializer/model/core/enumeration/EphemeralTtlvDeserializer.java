package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.enumeration.Ephemeral;

import java.io.IOException;
import java.nio.ByteBuffer;

public class EphemeralTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<Ephemeral, Ephemeral.EphemeralBuilder> {

    public EphemeralTtlvDeserializer() {
        super(Ephemeral.kmipTag, Ephemeral.encodingType);
    }

    @Override
    protected Ephemeral.EphemeralBuilder createBuilder() {
        return Ephemeral.builder();
    }

    @Override
    protected void setValue(Ephemeral.EphemeralBuilder builder, byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
        Integer value = mapper.readValue(p, Integer.class);
        builder.value(Ephemeral.fromValue(value));
    }

    @Override
    protected Ephemeral build(Ephemeral.EphemeralBuilder builder) {
        return builder.build();
    }
}
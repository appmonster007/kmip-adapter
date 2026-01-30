package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.enumeration.TicketType;

import java.io.IOException;
import java.nio.ByteBuffer;

public class TicketTypeTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<TicketType, TicketType.TicketTypeBuilder> {

    public TicketTypeTtlvDeserializer() {
        super(TicketType.kmipTag, TicketType.encodingType);
    }

    @Override
    protected TicketType.TicketTypeBuilder createBuilder() {
        return TicketType.builder();
    }

    @Override
    protected void setValue(TicketType.TicketTypeBuilder builder, byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
        Integer value = mapper.readValue(p, Integer.class);
        builder.value(TicketType.fromValue(value));
    }

    @Override
    protected TicketType build(TicketType.TicketTypeBuilder builder) {
        return builder.build();
    }
}

package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.TicketValue;

import java.io.IOException;
import java.nio.ByteBuffer;

public class TicketValueTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<TicketValue, TicketValue.TicketValueBuilder> {

    public TicketValueTtlvDeserializer() {
        super(TicketValue.kmipTag, TicketValue.encodingType);
    }

    @Override
    protected TicketValue.TicketValueBuilder createBuilder() {
        return TicketValue.builder();
    }

    @Override
    protected void setValue(TicketValue.TicketValueBuilder builder, byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
        builder.value(mapper.readValue(p, ByteBuffer.class));
    }

    @Override
    protected TicketValue build(TicketValue.TicketValueBuilder builder) {
        return builder.build();
    }
}

package org.purpleBean.kmip.codec.json.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.type.TicketValue;

import java.io.IOException;
import java.nio.ByteBuffer;

public class TicketValueJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<TicketValue, TicketValue.TicketValueBuilder> {

    public TicketValueJsonDeserializer() {
        super(TicketValue.kmipTag, TicketValue.encodingType);
    }

    @Override
    protected TicketValue.TicketValueBuilder createBuilder() {
        return TicketValue.builder();
    }

    @Override
    protected void setValue(TicketValue.TicketValueBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(ctxt.readValue(p, ByteBuffer.class));
    }

    @Override
    protected TicketValue build(TicketValue.TicketValueBuilder builder) {
        return builder.build();
    }
}

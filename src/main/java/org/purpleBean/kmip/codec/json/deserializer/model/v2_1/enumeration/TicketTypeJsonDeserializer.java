package org.purpleBean.kmip.codec.json.deserializer.model.v2_1.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.v2_1.enumeration.TicketType;

import java.io.IOException;

public class TicketTypeJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<TicketType, TicketType.TicketTypeBuilder> {

    public TicketTypeJsonDeserializer() {
        super(TicketType.kmipTag, TicketType.encodingType);
    }

    @Override
    protected TicketType.TicketTypeBuilder createBuilder() {
        return TicketType.builder();
    }

    @Override
    protected void setValue(TicketType.TicketTypeBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(TicketType.fromName(ctxt.readValue(p, String.class)));
    }

    @Override
    protected TicketType build(TicketType.TicketTypeBuilder builder) {
        return builder.build();
    }
}

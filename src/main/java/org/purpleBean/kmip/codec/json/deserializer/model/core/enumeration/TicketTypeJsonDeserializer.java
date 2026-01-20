package org.purpleBean.kmip.codec.json.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.enumeration.TicketType;

public class TicketTypeJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<TicketType, String> {

    public TicketTypeJsonDeserializer() {
        super(TicketType.kmipTag, TicketType.encodingType, String.class, value -> TicketType.fromName(value).inst());
    }
}
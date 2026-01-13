package org.purpleBean.kmip.codec.json.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.deserializer.kmip.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.enumeration.TicketType;

public class TicketTypeJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<TicketType, String> {

    public TicketTypeJsonDeserializer() {
        super(TicketType.kmipTag, TicketType.encodingType, String.class, value -> new TicketType(TicketType.fromName(value)));
    }
}
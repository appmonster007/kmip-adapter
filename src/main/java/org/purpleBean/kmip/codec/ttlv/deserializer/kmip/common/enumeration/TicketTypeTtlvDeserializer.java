package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.AbstractKmipTtlvDeserializer;
import org.purpleBean.kmip.common.enumeration.TicketType;

public class TicketTypeTtlvDeserializer extends AbstractKmipTtlvDeserializer<TicketType, Integer> {

    public TicketTypeTtlvDeserializer() {
        super(TicketType.kmipTag, TicketType.encodingType, Integer.class, value -> new TicketType(TicketType.fromValue(value)));
    }
}
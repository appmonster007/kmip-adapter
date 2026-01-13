package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.common.enumeration.TicketType;

public class TicketTypeTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<TicketType, Integer> {

    public TicketTypeTtlvDeserializer() {
        super(TicketType.kmipTag, TicketType.encodingType, Integer.class, value -> new TicketType(TicketType.fromValue(value)));
    }
}
package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.model.core.enumeration.TicketType;

public class TicketTypeTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<TicketType, Integer> {

    public TicketTypeTtlvDeserializer() {
        super(TicketType.kmipTag, TicketType.encodingType, Integer.class, value -> TicketType.fromValue(value).inst());
    }
}
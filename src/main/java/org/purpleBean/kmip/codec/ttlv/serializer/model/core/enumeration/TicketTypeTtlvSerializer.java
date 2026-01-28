package org.purpleBean.kmip.codec.ttlv.serializer.model.core.enumeration;

import org.purpleBean.kmip.codec.ttlv.serializer.api.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.model.core.enumeration.TicketType;

public class TicketTypeTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<TicketType, Integer> {

    public TicketTypeTtlvSerializer() {
        super(TicketType::getIntValue);
    }
}
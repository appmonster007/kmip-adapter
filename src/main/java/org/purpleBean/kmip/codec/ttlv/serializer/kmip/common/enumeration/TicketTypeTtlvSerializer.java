package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.common.enumeration.TicketType;

public class TicketTypeTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<TicketType, Integer> {

    public TicketTypeTtlvSerializer() {
        super(value -> value.getValue().getValue());
    }
}
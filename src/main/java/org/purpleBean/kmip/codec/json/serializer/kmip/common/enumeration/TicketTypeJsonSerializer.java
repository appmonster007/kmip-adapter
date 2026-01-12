package org.purpleBean.kmip.codec.json.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.serializer.AbstractKmipJsonSerializer;
import org.purpleBean.kmip.common.enumeration.TicketType;

public class TicketTypeJsonSerializer extends AbstractKmipJsonSerializer<TicketType, String> {

    public TicketTypeJsonSerializer() {
        super(TicketType::getDescription);
    }
}
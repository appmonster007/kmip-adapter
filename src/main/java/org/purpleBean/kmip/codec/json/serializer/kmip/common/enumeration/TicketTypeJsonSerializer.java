package org.purpleBean.kmip.codec.json.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.enumeration.TicketType;

public class TicketTypeJsonSerializer extends AbstractKmipDataTypeJsonSerializer<TicketType, String> {

    public TicketTypeJsonSerializer() {
        super(TicketType::getDescription);
    }
}
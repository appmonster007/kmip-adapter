package org.purpleBean.kmip.codec.json.serializer.model.core.enumeration;

import org.purpleBean.kmip.codec.json.serializer.api.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.model.core.enumeration.TicketType;

public class TicketTypeJsonSerializer extends AbstractKmipDataTypeJsonSerializer<TicketType, String> {

    public TicketTypeJsonSerializer() {
        super(TicketType::getDescription);
    }
}
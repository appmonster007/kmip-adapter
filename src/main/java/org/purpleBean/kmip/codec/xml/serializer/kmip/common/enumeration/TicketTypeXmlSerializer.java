package org.purpleBean.kmip.codec.xml.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;
import org.purpleBean.kmip.common.enumeration.TicketType;

public class TicketTypeXmlSerializer extends AbstractKmipXmlSerializer<TicketType, String> {

    public TicketTypeXmlSerializer() {
        super(TicketType::getDescription);
    }
}
package org.purpleBean.kmip.codec.xml.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.enumeration.TicketType;

public class TicketTypeXmlSerializer extends AbstractKmipDataTypeXmlSerializer<TicketType, String> {

    public TicketTypeXmlSerializer() {
        super(TicketType::getDescription);
    }
}
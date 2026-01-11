package org.purpleBean.kmip.codec.xml.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.xml.deserializer.AbstractKmipXmlDeserializer;
import org.purpleBean.kmip.common.enumeration.TicketType;

public class TicketTypeXmlDeserializer extends AbstractKmipXmlDeserializer<TicketType, String> {

    public TicketTypeXmlDeserializer() {
        super(TicketType.kmipTag, TicketType.encodingType, String.class, value -> new TicketType(TicketType.fromName(value)));
    }
}
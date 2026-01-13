package org.purpleBean.kmip.codec.xml.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.xml.deserializer.kmip.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.common.enumeration.TicketType;

public class TicketTypeXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<TicketType, String> {

    public TicketTypeXmlDeserializer() {
        super(TicketType.kmipTag, TicketType.encodingType, String.class, value -> new TicketType(TicketType.fromName(value)));
    }
}
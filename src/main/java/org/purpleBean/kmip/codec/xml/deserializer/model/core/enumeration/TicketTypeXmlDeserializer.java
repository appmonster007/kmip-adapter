package org.purpleBean.kmip.codec.xml.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.enumeration.TicketType;

public class TicketTypeXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<TicketType, String> {

    public TicketTypeXmlDeserializer() {
        super(TicketType.kmipTag, TicketType.encodingType, String.class, value -> TicketType.fromName(value).inst());
    }
}
package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.common.CompromiseDate;
import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;

import java.time.OffsetDateTime;

public class CompromiseDateXmlSerializer extends AbstractKmipXmlSerializer<CompromiseDate, OffsetDateTime> {

    public CompromiseDateXmlSerializer() {
        super(CompromiseDate::getValue);
    }
}
package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.CompromiseDate;

import java.time.OffsetDateTime;

public class CompromiseDateXmlSerializer extends AbstractKmipDataTypeXmlSerializer<CompromiseDate, OffsetDateTime> {

    public CompromiseDateXmlSerializer() {
        super(CompromiseDate::getValue);
    }
}
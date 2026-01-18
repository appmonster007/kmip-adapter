package org.purpleBean.kmip.codec.xml.serializer.model.core.type;

import org.purpleBean.kmip.codec.xml.serializer.api.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.model.core.type.CompromiseDate;

import java.time.OffsetDateTime;

public class CompromiseDateXmlSerializer extends AbstractKmipDataTypeXmlSerializer<CompromiseDate, OffsetDateTime> {

    public CompromiseDateXmlSerializer() {
        super(CompromiseDate::getValue);
    }
}
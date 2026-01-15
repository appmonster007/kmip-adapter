package org.purpleBean.kmip.codec.xml.deserializer.model.core.type;

import org.purpleBean.kmip.codec.xml.deserializer.kmip.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.type.CompromiseDate;

import java.time.OffsetDateTime;

public class CompromiseDateXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<CompromiseDate, OffsetDateTime> {

    public CompromiseDateXmlDeserializer() {
        super(CompromiseDate.kmipTag, CompromiseDate.encodingType, OffsetDateTime.class, value -> CompromiseDate.builder().value(value).build());
    }
}
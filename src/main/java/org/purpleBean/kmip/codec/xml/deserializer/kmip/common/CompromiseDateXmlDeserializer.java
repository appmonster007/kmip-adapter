package org.purpleBean.kmip.codec.xml.deserializer.kmip.common;

import org.purpleBean.kmip.codec.xml.deserializer.kmip.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.common.CompromiseDate;

import java.time.OffsetDateTime;

public class CompromiseDateXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<CompromiseDate, OffsetDateTime> {

    public CompromiseDateXmlDeserializer() {
        super(CompromiseDate.kmipTag, CompromiseDate.encodingType, OffsetDateTime.class, value -> CompromiseDate.builder().value(value).build());
    }
}
package org.purpleBean.kmip.codec.xml.deserializer.kmip.common;

import org.purpleBean.kmip.codec.xml.deserializer.AbstractKmipXmlDeserializer;
import org.purpleBean.kmip.common.CompromiseOccurrenceDate;

import java.time.OffsetDateTime;

public class CompromiseOccurrenceDateXmlDeserializer extends AbstractKmipXmlDeserializer<CompromiseOccurrenceDate, OffsetDateTime> {

    public CompromiseOccurrenceDateXmlDeserializer() {
        super(CompromiseOccurrenceDate.kmipTag, CompromiseOccurrenceDate.encodingType, OffsetDateTime.class, value -> CompromiseOccurrenceDate.builder().value(value).build());
    }
}
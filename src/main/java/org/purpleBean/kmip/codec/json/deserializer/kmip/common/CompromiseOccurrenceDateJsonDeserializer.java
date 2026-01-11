package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import org.purpleBean.kmip.codec.json.deserializer.AbstractKmipJsonDeserializer;
import org.purpleBean.kmip.common.CompromiseOccurrenceDate;

import java.time.OffsetDateTime;

public class CompromiseOccurrenceDateJsonDeserializer extends AbstractKmipJsonDeserializer<CompromiseOccurrenceDate, OffsetDateTime> {

    public CompromiseOccurrenceDateJsonDeserializer() {
        super(CompromiseOccurrenceDate.kmipTag, CompromiseOccurrenceDate.encodingType, OffsetDateTime.class, value -> CompromiseOccurrenceDate.builder().value(value).build());
    }
}
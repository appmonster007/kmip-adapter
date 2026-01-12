package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.deserializer.AbstractKmipTtlvDeserializer;
import org.purpleBean.kmip.common.CompromiseOccurrenceDate;

import java.time.OffsetDateTime;

public class CompromiseOccurrenceDateTtlvDeserializer extends AbstractKmipTtlvDeserializer<CompromiseOccurrenceDate, OffsetDateTime> {

    public CompromiseOccurrenceDateTtlvDeserializer() {
        super(CompromiseOccurrenceDate.kmipTag, CompromiseOccurrenceDate.encodingType, OffsetDateTime.class, value -> CompromiseOccurrenceDate.builder().value(value).build());
    }
}
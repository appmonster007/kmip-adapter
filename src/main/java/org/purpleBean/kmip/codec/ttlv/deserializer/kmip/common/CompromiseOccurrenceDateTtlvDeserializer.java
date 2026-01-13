package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.common.CompromiseOccurrenceDate;

import java.time.OffsetDateTime;

public class CompromiseOccurrenceDateTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<CompromiseOccurrenceDate, OffsetDateTime> {

    public CompromiseOccurrenceDateTtlvDeserializer() {
        super(CompromiseOccurrenceDate.kmipTag, CompromiseOccurrenceDate.encodingType, OffsetDateTime.class, value -> CompromiseOccurrenceDate.builder().value(value).build());
    }
}
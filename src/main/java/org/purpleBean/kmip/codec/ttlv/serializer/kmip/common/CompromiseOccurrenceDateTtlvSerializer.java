package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.common.CompromiseOccurrenceDate;

import java.time.OffsetDateTime;

public class CompromiseOccurrenceDateTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<CompromiseOccurrenceDate, OffsetDateTime> {

    public CompromiseOccurrenceDateTtlvSerializer() {
        super(CompromiseOccurrenceDate::getValue);
    }
}
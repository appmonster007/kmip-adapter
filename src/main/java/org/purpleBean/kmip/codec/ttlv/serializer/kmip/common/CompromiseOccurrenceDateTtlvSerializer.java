package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.AbstractKmipTtlvSerializer;
import org.purpleBean.kmip.common.CompromiseOccurrenceDate;

import java.time.OffsetDateTime;

public class CompromiseOccurrenceDateTtlvSerializer extends AbstractKmipTtlvSerializer<CompromiseOccurrenceDate, OffsetDateTime> {

    public CompromiseOccurrenceDateTtlvSerializer() {
        super(CompromiseOccurrenceDate::getValue);
    }
}
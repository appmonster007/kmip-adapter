package org.purpleBean.kmip.codec.ttlv.serializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.serializer.api.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.model.core.type.CompromiseOccurrenceDate;

import java.time.OffsetDateTime;

public class CompromiseOccurrenceDateTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<CompromiseOccurrenceDate, OffsetDateTime> {

    public CompromiseOccurrenceDateTtlvSerializer() {
        super(CompromiseOccurrenceDate::getValue);
    }
}
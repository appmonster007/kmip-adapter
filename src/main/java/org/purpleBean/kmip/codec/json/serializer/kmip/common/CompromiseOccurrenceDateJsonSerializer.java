package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.CompromiseOccurrenceDate;

import java.time.OffsetDateTime;

public class CompromiseOccurrenceDateJsonSerializer extends AbstractKmipDataTypeJsonSerializer<CompromiseOccurrenceDate, OffsetDateTime> {

    public CompromiseOccurrenceDateJsonSerializer() {
        super(CompromiseOccurrenceDate::getValue);
    }
}
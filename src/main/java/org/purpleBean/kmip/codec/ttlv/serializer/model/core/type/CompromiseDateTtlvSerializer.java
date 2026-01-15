package org.purpleBean.kmip.codec.ttlv.serializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.model.core.type.CompromiseDate;

import java.time.OffsetDateTime;

public class CompromiseDateTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<CompromiseDate, OffsetDateTime> {

    public CompromiseDateTtlvSerializer() {
        super(CompromiseDate::getValue);
    }
}
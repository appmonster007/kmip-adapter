package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.AbstractKmipTtlvSerializer;
import org.purpleBean.kmip.common.CompromiseDate;

import java.time.OffsetDateTime;

public class CompromiseDateTtlvSerializer extends AbstractKmipTtlvSerializer<CompromiseDate, OffsetDateTime> {

    public CompromiseDateTtlvSerializer() {
        super(CompromiseDate::getValue);
    }
}
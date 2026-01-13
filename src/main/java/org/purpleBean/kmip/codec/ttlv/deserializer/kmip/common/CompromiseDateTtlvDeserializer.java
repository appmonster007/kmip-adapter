package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.common.CompromiseDate;

import java.time.OffsetDateTime;

public class CompromiseDateTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<CompromiseDate, OffsetDateTime> {

    public CompromiseDateTtlvDeserializer() {
        super(CompromiseDate.kmipTag, CompromiseDate.encodingType, OffsetDateTime.class, value -> CompromiseDate.builder().value(value).build());
    }
}
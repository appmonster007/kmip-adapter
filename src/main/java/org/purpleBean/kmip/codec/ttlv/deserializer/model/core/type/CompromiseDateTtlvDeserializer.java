package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.model.core.type.CompromiseDate;

import java.time.OffsetDateTime;

public class CompromiseDateTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<CompromiseDate, OffsetDateTime> {

    public CompromiseDateTtlvDeserializer() {
        super(CompromiseDate.kmipTag, CompromiseDate.encodingType, OffsetDateTime.class, value -> CompromiseDate.builder().value(value).build());
    }
}
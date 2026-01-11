package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import org.purpleBean.kmip.codec.json.deserializer.AbstractKmipJsonDeserializer;
import org.purpleBean.kmip.common.CompromiseDate;

import java.time.OffsetDateTime;

public class CompromiseDateJsonDeserializer extends AbstractKmipJsonDeserializer<CompromiseDate, OffsetDateTime> {

    public CompromiseDateJsonDeserializer() {
        super(CompromiseDate.kmipTag, CompromiseDate.encodingType, OffsetDateTime.class, value -> CompromiseDate.builder().value(value).build());
    }
}
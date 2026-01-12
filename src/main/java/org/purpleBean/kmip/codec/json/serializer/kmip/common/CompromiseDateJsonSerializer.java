package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.AbstractKmipJsonSerializer;
import org.purpleBean.kmip.common.CompromiseDate;

import java.time.OffsetDateTime;

public class CompromiseDateJsonSerializer extends AbstractKmipJsonSerializer<CompromiseDate, OffsetDateTime> {

    public CompromiseDateJsonSerializer() {
        super(CompromiseDate::getValue);
    }
}
package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.CompromiseDate;

import java.time.OffsetDateTime;

public class CompromiseDateJsonSerializer extends AbstractKmipDataTypeJsonSerializer<CompromiseDate, OffsetDateTime> {

    public CompromiseDateJsonSerializer() {
        super(CompromiseDate::getValue);
    }
}
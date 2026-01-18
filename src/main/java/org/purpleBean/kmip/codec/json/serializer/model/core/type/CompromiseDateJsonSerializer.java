package org.purpleBean.kmip.codec.json.serializer.model.core.type;

import org.purpleBean.kmip.codec.json.serializer.api.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.model.core.type.CompromiseDate;

import java.time.OffsetDateTime;

public class CompromiseDateJsonSerializer extends AbstractKmipDataTypeJsonSerializer<CompromiseDate, OffsetDateTime> {

    public CompromiseDateJsonSerializer() {
        super(CompromiseDate::getValue);
    }
}
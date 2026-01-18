package org.purpleBean.kmip.codec.json.serializer.model.core.type;

import org.purpleBean.kmip.codec.json.serializer.api.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.model.core.type.LastChangeDate;

import java.time.OffsetDateTime;

public class LastChangeDateJsonSerializer extends AbstractKmipDataTypeJsonSerializer<LastChangeDate, OffsetDateTime> {

    public LastChangeDateJsonSerializer() {
        super(LastChangeDate::getValue);
    }
}
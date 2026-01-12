package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.AbstractKmipJsonSerializer;
import org.purpleBean.kmip.common.LastChangeDate;

import java.time.OffsetDateTime;

public class LastChangeDateJsonSerializer extends AbstractKmipJsonSerializer<LastChangeDate, OffsetDateTime> {

    public LastChangeDateJsonSerializer() {
        super(LastChangeDate::getValue);
    }
}
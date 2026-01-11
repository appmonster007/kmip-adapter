package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import org.purpleBean.kmip.codec.json.deserializer.AbstractKmipJsonDeserializer;
import org.purpleBean.kmip.common.LastChangeDate;

import java.time.OffsetDateTime;

public class LastChangeDateJsonDeserializer extends AbstractKmipJsonDeserializer<LastChangeDate, OffsetDateTime> {

    public LastChangeDateJsonDeserializer() {
        super(LastChangeDate.kmipTag, LastChangeDate.encodingType, OffsetDateTime.class, value -> LastChangeDate.builder().value(value).build());
    }
}
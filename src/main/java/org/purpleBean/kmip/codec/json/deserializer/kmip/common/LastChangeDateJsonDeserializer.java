package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import org.purpleBean.kmip.codec.json.deserializer.kmip.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.LastChangeDate;

import java.time.OffsetDateTime;

public class LastChangeDateJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<LastChangeDate, OffsetDateTime> {

    public LastChangeDateJsonDeserializer() {
        super(LastChangeDate.kmipTag, LastChangeDate.encodingType, OffsetDateTime.class, value -> LastChangeDate.builder().value(value).build());
    }
}
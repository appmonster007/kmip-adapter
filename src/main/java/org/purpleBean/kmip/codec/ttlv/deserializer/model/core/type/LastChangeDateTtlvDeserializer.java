package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.model.core.type.LastChangeDate;

import java.time.OffsetDateTime;

public class LastChangeDateTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<LastChangeDate, OffsetDateTime> {

    public LastChangeDateTtlvDeserializer() {
        super(LastChangeDate.kmipTag, LastChangeDate.encodingType, OffsetDateTime.class, value -> LastChangeDate.builder().value(value).build());
    }
}
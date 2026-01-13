package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.common.LastChangeDate;

import java.time.OffsetDateTime;

public class LastChangeDateTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<LastChangeDate, OffsetDateTime> {

    public LastChangeDateTtlvDeserializer() {
        super(LastChangeDate.kmipTag, LastChangeDate.encodingType, OffsetDateTime.class, value -> LastChangeDate.builder().value(value).build());
    }
}
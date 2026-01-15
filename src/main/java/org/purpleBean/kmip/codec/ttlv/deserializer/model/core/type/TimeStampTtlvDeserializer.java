package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.model.core.type.TimeStamp;

import java.time.OffsetDateTime;

public class TimeStampTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<TimeStamp, OffsetDateTime> {

    public TimeStampTtlvDeserializer() {
        super(TimeStamp.kmipTag, TimeStamp.encodingType, OffsetDateTime.class, value -> TimeStamp.builder().value(value).build());
    }
}
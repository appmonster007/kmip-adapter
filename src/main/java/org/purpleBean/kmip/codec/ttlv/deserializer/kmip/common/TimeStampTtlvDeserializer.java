package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.deserializer.AbstractKmipTtlvDeserializer;
import org.purpleBean.kmip.common.TimeStamp;

import java.time.OffsetDateTime;

public class TimeStampTtlvDeserializer extends AbstractKmipTtlvDeserializer<TimeStamp, OffsetDateTime> {

    public TimeStampTtlvDeserializer() {
        super(TimeStamp.kmipTag, TimeStamp.encodingType, OffsetDateTime.class, value -> TimeStamp.builder().value(value).build());
    }
}
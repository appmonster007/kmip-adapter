package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.common.TimeStamp;

import java.time.OffsetDateTime;

public class TimeStampTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<TimeStamp, OffsetDateTime> {

    public TimeStampTtlvDeserializer() {
        super(TimeStamp.kmipTag, TimeStamp.encodingType, OffsetDateTime.class, value -> TimeStamp.builder().value(value).build());
    }
}
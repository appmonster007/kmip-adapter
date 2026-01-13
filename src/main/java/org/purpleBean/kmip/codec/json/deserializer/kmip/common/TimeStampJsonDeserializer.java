package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import org.purpleBean.kmip.codec.json.deserializer.kmip.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.TimeStamp;

import java.time.OffsetDateTime;

public class TimeStampJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<TimeStamp, OffsetDateTime> {

    public TimeStampJsonDeserializer() {
        super(TimeStamp.kmipTag, TimeStamp.encodingType, OffsetDateTime.class, value -> TimeStamp.builder().value(value).build());
    }
}
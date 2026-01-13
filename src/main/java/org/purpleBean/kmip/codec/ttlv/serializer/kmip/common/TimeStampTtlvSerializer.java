package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.common.TimeStamp;

import java.time.OffsetDateTime;

public class TimeStampTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<TimeStamp, OffsetDateTime> {

    public TimeStampTtlvSerializer() {
        super(TimeStamp::getValue);
    }
}
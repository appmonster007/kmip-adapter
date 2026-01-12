package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.AbstractKmipTtlvSerializer;
import org.purpleBean.kmip.common.TimeStamp;

import java.time.OffsetDateTime;

public class TimeStampTtlvSerializer extends AbstractKmipTtlvSerializer<TimeStamp, OffsetDateTime> {

    public TimeStampTtlvSerializer() {
        super(TimeStamp::getValue);
    }
}
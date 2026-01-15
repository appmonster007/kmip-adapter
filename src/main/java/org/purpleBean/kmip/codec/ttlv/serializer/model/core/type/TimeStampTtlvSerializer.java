package org.purpleBean.kmip.codec.ttlv.serializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.model.core.type.TimeStamp;

import java.time.OffsetDateTime;

public class TimeStampTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<TimeStamp, OffsetDateTime> {

    public TimeStampTtlvSerializer() {
        super(TimeStamp::getValue);
    }
}
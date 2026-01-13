package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.TimeStamp;

import java.time.OffsetDateTime;

public class TimeStampJsonSerializer extends AbstractKmipDataTypeJsonSerializer<TimeStamp, OffsetDateTime> {

    public TimeStampJsonSerializer() {
        super(TimeStamp::getValue);
    }
}
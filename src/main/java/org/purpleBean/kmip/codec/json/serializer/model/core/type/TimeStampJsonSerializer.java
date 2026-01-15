package org.purpleBean.kmip.codec.json.serializer.model.core.type;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.model.core.type.TimeStamp;

import java.time.OffsetDateTime;

public class TimeStampJsonSerializer extends AbstractKmipDataTypeJsonSerializer<TimeStamp, OffsetDateTime> {

    public TimeStampJsonSerializer() {
        super(TimeStamp::getValue);
    }
}
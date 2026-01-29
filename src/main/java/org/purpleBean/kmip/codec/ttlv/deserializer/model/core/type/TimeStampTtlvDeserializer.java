package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.TimeStamp;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.time.OffsetDateTime;

public class TimeStampTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<TimeStamp, TimeStamp.TimeStampBuilder> {

    public TimeStampTtlvDeserializer() {
        super(TimeStamp.kmipTag, TimeStamp.encodingType);
    }

    @Override
    protected TimeStamp.TimeStampBuilder createBuilder() {
        return TimeStamp.builder();
    }

    @Override
    protected void setValue(TimeStamp.TimeStampBuilder builder, byte[] tagBytes, ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
        builder.value(mapper.readValue(byteBuffer, OffsetDateTime.class));
    }

    @Override
    protected TimeStamp build(TimeStamp.TimeStampBuilder builder) {
        return builder.build();
    }
}

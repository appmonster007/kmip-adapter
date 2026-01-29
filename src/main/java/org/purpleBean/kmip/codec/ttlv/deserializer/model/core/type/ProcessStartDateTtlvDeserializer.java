package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.ProcessStartDate;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.time.OffsetDateTime;

public class ProcessStartDateTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<ProcessStartDate, ProcessStartDate.ProcessStartDateBuilder> {

    public ProcessStartDateTtlvDeserializer() {
        super(ProcessStartDate.kmipTag, ProcessStartDate.encodingType);
    }

    @Override
    protected ProcessStartDate.ProcessStartDateBuilder createBuilder() {
        return ProcessStartDate.builder();
    }

    @Override
    protected void setValue(ProcessStartDate.ProcessStartDateBuilder builder, byte[] tagBytes, ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
        builder.value(mapper.readValue(byteBuffer, OffsetDateTime.class));
    }

    @Override
    protected ProcessStartDate build(ProcessStartDate.ProcessStartDateBuilder builder) {
        return builder.build();
    }
}
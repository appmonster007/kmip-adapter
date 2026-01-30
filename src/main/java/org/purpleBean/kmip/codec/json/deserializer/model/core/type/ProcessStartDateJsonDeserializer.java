package org.purpleBean.kmip.codec.json.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.type.ProcessStartDate;

import java.io.IOException;
import java.time.OffsetDateTime;

public class ProcessStartDateJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<ProcessStartDate, ProcessStartDate.ProcessStartDateBuilder> {

    public ProcessStartDateJsonDeserializer() {
        super(ProcessStartDate.kmipTag, ProcessStartDate.encodingType);
    }

    @Override
    protected ProcessStartDate.ProcessStartDateBuilder createBuilder() {
        return ProcessStartDate.builder();
    }

    @Override
    protected void setValue(ProcessStartDate.ProcessStartDateBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(ctxt.readValue(p, OffsetDateTime.class));
    }

    @Override
    protected ProcessStartDate build(ProcessStartDate.ProcessStartDateBuilder builder) {
        return builder.build();
    }
}

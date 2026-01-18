package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.model.core.type.ProcessStartDate;

import java.time.OffsetDateTime;

public class ProcessStartDateTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<ProcessStartDate, OffsetDateTime> {

    public ProcessStartDateTtlvDeserializer() {
        super(ProcessStartDate.kmipTag, ProcessStartDate.encodingType, OffsetDateTime.class, value -> ProcessStartDate.builder().value(value).build());
    }
}
package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.deserializer.AbstractKmipTtlvDeserializer;
import org.purpleBean.kmip.common.ProcessStartDate;

import java.time.OffsetDateTime;

public class ProcessStartDateTtlvDeserializer extends AbstractKmipTtlvDeserializer<ProcessStartDate, OffsetDateTime> {

    public ProcessStartDateTtlvDeserializer() {
        super(ProcessStartDate.kmipTag, ProcessStartDate.encodingType, OffsetDateTime.class, value -> ProcessStartDate.builder().value(value).build());
    }
}
package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import org.purpleBean.kmip.codec.json.deserializer.AbstractKmipJsonDeserializer;
import org.purpleBean.kmip.common.ProcessStartDate;

import java.time.OffsetDateTime;

public class ProcessStartDateJsonDeserializer extends AbstractKmipJsonDeserializer<ProcessStartDate, OffsetDateTime> {

    public ProcessStartDateJsonDeserializer() {
        super(ProcessStartDate.kmipTag, ProcessStartDate.encodingType, OffsetDateTime.class, value -> ProcessStartDate.builder().value(value).build());
    }
}
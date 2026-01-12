package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.AbstractKmipJsonSerializer;
import org.purpleBean.kmip.common.ProcessStartDate;

import java.time.OffsetDateTime;

public class ProcessStartDateJsonSerializer extends AbstractKmipJsonSerializer<ProcessStartDate, OffsetDateTime> {

    public ProcessStartDateJsonSerializer() {
        super(ProcessStartDate::getValue);
    }
}
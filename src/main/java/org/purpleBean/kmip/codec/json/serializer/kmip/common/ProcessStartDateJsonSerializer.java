package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.ProcessStartDate;

import java.time.OffsetDateTime;

public class ProcessStartDateJsonSerializer extends AbstractKmipDataTypeJsonSerializer<ProcessStartDate, OffsetDateTime> {

    public ProcessStartDateJsonSerializer() {
        super(ProcessStartDate::getValue);
    }
}
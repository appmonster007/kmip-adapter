package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.AbstractKmipTtlvSerializer;
import org.purpleBean.kmip.common.ProcessStartDate;

import java.time.OffsetDateTime;

public class ProcessStartDateTtlvSerializer extends AbstractKmipTtlvSerializer<ProcessStartDate, OffsetDateTime> {

    public ProcessStartDateTtlvSerializer() {
        super(ProcessStartDate::getValue);
    }
}
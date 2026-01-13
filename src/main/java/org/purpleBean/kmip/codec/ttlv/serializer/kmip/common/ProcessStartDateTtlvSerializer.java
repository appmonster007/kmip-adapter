package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.common.ProcessStartDate;

import java.time.OffsetDateTime;

public class ProcessStartDateTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<ProcessStartDate, OffsetDateTime> {

    public ProcessStartDateTtlvSerializer() {
        super(ProcessStartDate::getValue);
    }
}
package org.purpleBean.kmip.codec.ttlv.serializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.serializer.api.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.model.core.type.ProcessStartDate;

import java.time.OffsetDateTime;

public class ProcessStartDateTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<ProcessStartDate, OffsetDateTime> {

    public ProcessStartDateTtlvSerializer() {
        super(ProcessStartDate::getValue);
    }
}
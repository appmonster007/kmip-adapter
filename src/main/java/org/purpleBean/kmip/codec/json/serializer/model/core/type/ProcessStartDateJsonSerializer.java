package org.purpleBean.kmip.codec.json.serializer.model.core.type;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.model.core.type.ProcessStartDate;

import java.time.OffsetDateTime;

public class ProcessStartDateJsonSerializer extends AbstractKmipDataTypeJsonSerializer<ProcessStartDate, OffsetDateTime> {

    public ProcessStartDateJsonSerializer() {
        super(ProcessStartDate::getValue);
    }
}
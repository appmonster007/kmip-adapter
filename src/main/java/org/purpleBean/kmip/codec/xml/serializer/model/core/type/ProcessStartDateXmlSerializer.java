package org.purpleBean.kmip.codec.xml.serializer.model.core.type;

import org.purpleBean.kmip.codec.xml.serializer.api.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.model.core.type.ProcessStartDate;

import java.time.OffsetDateTime;

public class ProcessStartDateXmlSerializer extends AbstractKmipDataTypeXmlSerializer<ProcessStartDate, OffsetDateTime> {

    public ProcessStartDateXmlSerializer() {
        super(ProcessStartDate::getValue);
    }
}
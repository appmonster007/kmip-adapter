package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.common.ProcessStartDate;
import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;

import java.time.OffsetDateTime;

public class ProcessStartDateXmlSerializer extends AbstractKmipXmlSerializer<ProcessStartDate, OffsetDateTime> {

    public ProcessStartDateXmlSerializer() {
        super(ProcessStartDate::getValue);
    }
}
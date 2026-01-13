package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.ProcessStartDate;

import java.time.OffsetDateTime;

public class ProcessStartDateXmlSerializer extends AbstractKmipDataTypeXmlSerializer<ProcessStartDate, OffsetDateTime> {

    public ProcessStartDateXmlSerializer() {
        super(ProcessStartDate::getValue);
    }
}
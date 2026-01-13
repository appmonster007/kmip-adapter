package org.purpleBean.kmip.codec.xml.deserializer.kmip.common;

import org.purpleBean.kmip.codec.xml.deserializer.kmip.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.common.ProcessStartDate;

import java.time.OffsetDateTime;

public class ProcessStartDateXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<ProcessStartDate, OffsetDateTime> {

    public ProcessStartDateXmlDeserializer() {
        super(ProcessStartDate.kmipTag, ProcessStartDate.encodingType, OffsetDateTime.class, value -> ProcessStartDate.builder().value(value).build());
    }
}
package org.purpleBean.kmip.codec.xml.deserializer.model.core.type;

import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.type.ProcessStartDate;

import java.time.OffsetDateTime;

public class ProcessStartDateXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<ProcessStartDate, OffsetDateTime> {

    public ProcessStartDateXmlDeserializer() {
        super(ProcessStartDate.kmipTag, ProcessStartDate.encodingType, OffsetDateTime.class, value -> ProcessStartDate.builder().value(value).build());
    }
}
package org.purpleBean.kmip.codec.xml.deserializer.model.core.type;

import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.type.TimeStamp;

import java.time.OffsetDateTime;

public class TimeStampXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<TimeStamp, OffsetDateTime> {

    public TimeStampXmlDeserializer() {
        super(TimeStamp.kmipTag, TimeStamp.encodingType, OffsetDateTime.class, value -> TimeStamp.builder().value(value).build());
    }
}
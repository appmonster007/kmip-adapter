package org.purpleBean.kmip.codec.xml.deserializer.kmip.common;

import org.purpleBean.kmip.codec.xml.deserializer.kmip.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.common.TimeStamp;

import java.time.OffsetDateTime;

public class TimeStampXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<TimeStamp, OffsetDateTime> {

    public TimeStampXmlDeserializer() {
        super(TimeStamp.kmipTag, TimeStamp.encodingType, OffsetDateTime.class, value -> TimeStamp.builder().value(value).build());
    }
}
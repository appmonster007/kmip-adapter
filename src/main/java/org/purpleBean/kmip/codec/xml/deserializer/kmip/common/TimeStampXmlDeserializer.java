package org.purpleBean.kmip.codec.xml.deserializer.kmip.common;

import org.purpleBean.kmip.codec.xml.deserializer.AbstractKmipXmlDeserializer;
import org.purpleBean.kmip.common.TimeStamp;

import java.time.OffsetDateTime;

public class TimeStampXmlDeserializer extends AbstractKmipXmlDeserializer<TimeStamp, OffsetDateTime> {

    public TimeStampXmlDeserializer() {
        super(TimeStamp.kmipTag, TimeStamp.encodingType, OffsetDateTime.class, value -> TimeStamp.builder().value(value).build());
    }
}
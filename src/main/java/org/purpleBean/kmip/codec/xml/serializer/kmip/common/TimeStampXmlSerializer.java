package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;
import org.purpleBean.kmip.common.TimeStamp;

import java.time.OffsetDateTime;

public class TimeStampXmlSerializer extends AbstractKmipXmlSerializer<TimeStamp, OffsetDateTime> {

    public TimeStampXmlSerializer() {
        super(TimeStamp::getValue);
    }
}
package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.TimeStamp;

import java.time.OffsetDateTime;

public class TimeStampXmlSerializer extends AbstractKmipDataTypeXmlSerializer<TimeStamp, OffsetDateTime> {

    public TimeStampXmlSerializer() {
        super(TimeStamp::getValue);
    }
}
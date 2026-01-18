package org.purpleBean.kmip.codec.xml.serializer.model.core.type;

import org.purpleBean.kmip.codec.xml.serializer.api.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.model.core.type.TimeStamp;

import java.time.OffsetDateTime;

public class TimeStampXmlSerializer extends AbstractKmipDataTypeXmlSerializer<TimeStamp, OffsetDateTime> {

    public TimeStampXmlSerializer() {
        super(TimeStamp::getValue);
    }
}
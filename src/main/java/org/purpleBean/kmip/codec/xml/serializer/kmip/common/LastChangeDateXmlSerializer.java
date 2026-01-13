package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.LastChangeDate;

import java.time.OffsetDateTime;

public class LastChangeDateXmlSerializer extends AbstractKmipDataTypeXmlSerializer<LastChangeDate, OffsetDateTime> {

    public LastChangeDateXmlSerializer() {
        super(LastChangeDate::getValue);
    }
}
package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.common.LastChangeDate;
import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;

import java.time.OffsetDateTime;

public class LastChangeDateXmlSerializer extends AbstractKmipXmlSerializer<LastChangeDate, OffsetDateTime> {

    public LastChangeDateXmlSerializer() {
        super(LastChangeDate::getValue);
    }
}
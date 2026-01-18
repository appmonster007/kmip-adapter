package org.purpleBean.kmip.codec.xml.serializer.model.core.type;

import org.purpleBean.kmip.codec.xml.serializer.api.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.model.core.type.LastChangeDate;

import java.time.OffsetDateTime;

public class LastChangeDateXmlSerializer extends AbstractKmipDataTypeXmlSerializer<LastChangeDate, OffsetDateTime> {

    public LastChangeDateXmlSerializer() {
        super(LastChangeDate::getValue);
    }
}
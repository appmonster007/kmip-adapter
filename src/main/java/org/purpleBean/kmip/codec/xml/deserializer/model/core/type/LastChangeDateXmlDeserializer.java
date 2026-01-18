package org.purpleBean.kmip.codec.xml.deserializer.model.core.type;

import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.type.LastChangeDate;

import java.time.OffsetDateTime;

public class LastChangeDateXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<LastChangeDate, OffsetDateTime> {

    public LastChangeDateXmlDeserializer() {
        super(LastChangeDate.kmipTag, LastChangeDate.encodingType, OffsetDateTime.class, value -> LastChangeDate.builder().value(value).build());
    }
}
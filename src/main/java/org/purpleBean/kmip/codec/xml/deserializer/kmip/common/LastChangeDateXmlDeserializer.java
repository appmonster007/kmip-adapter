package org.purpleBean.kmip.codec.xml.deserializer.kmip.common;

import org.purpleBean.kmip.codec.xml.deserializer.kmip.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.common.LastChangeDate;

import java.time.OffsetDateTime;

public class LastChangeDateXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<LastChangeDate, OffsetDateTime> {

    public LastChangeDateXmlDeserializer() {
        super(LastChangeDate.kmipTag, LastChangeDate.encodingType, OffsetDateTime.class, value -> LastChangeDate.builder().value(value).build());
    }
}
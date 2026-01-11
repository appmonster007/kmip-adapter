package org.purpleBean.kmip.codec.xml.deserializer.kmip.common;

import org.purpleBean.kmip.codec.xml.deserializer.AbstractKmipXmlDeserializer;
import org.purpleBean.kmip.common.InitialDate;

import java.time.OffsetDateTime;

public class InitialDateXmlDeserializer extends AbstractKmipXmlDeserializer<InitialDate, OffsetDateTime> {

    public InitialDateXmlDeserializer() {
        super(InitialDate.kmipTag, InitialDate.encodingType, OffsetDateTime.class, value -> InitialDate.builder().value(value).build());
    }
}
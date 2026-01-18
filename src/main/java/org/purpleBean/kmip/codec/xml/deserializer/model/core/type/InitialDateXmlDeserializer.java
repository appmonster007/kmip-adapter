package org.purpleBean.kmip.codec.xml.deserializer.model.core.type;

import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.type.InitialDate;

import java.time.OffsetDateTime;

public class InitialDateXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<InitialDate, OffsetDateTime> {

    public InitialDateXmlDeserializer() {
        super(InitialDate.kmipTag, InitialDate.encodingType, OffsetDateTime.class, value -> InitialDate.builder().value(value).build());
    }
}
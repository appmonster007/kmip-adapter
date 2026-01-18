package org.purpleBean.kmip.codec.xml.deserializer.model.core.type;

import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.type.ActivationDate;

import java.time.OffsetDateTime;

public class ActivationDateXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<ActivationDate, OffsetDateTime> {

    public ActivationDateXmlDeserializer() {
        super(ActivationDate.kmipTag, ActivationDate.encodingType, OffsetDateTime.class, value -> ActivationDate.builder().value(value).build());
    }
}
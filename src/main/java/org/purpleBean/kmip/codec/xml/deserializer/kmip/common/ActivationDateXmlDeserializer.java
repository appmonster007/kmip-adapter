package org.purpleBean.kmip.codec.xml.deserializer.kmip.common;

import org.purpleBean.kmip.codec.xml.deserializer.AbstractKmipXmlDeserializer;
import org.purpleBean.kmip.common.ActivationDate;

import java.time.OffsetDateTime;

public class ActivationDateXmlDeserializer extends AbstractKmipXmlDeserializer<ActivationDate, OffsetDateTime> {

    public ActivationDateXmlDeserializer() {
        super(ActivationDate.kmipTag, ActivationDate.encodingType, OffsetDateTime.class, value -> ActivationDate.builder().value(value).build());
    }
}
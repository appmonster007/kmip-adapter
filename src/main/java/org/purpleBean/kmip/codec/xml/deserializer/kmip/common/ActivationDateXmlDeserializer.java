package org.purpleBean.kmip.codec.xml.deserializer.kmip.common;

import org.purpleBean.kmip.codec.xml.deserializer.kmip.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.common.ActivationDate;

import java.time.OffsetDateTime;

public class ActivationDateXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<ActivationDate, OffsetDateTime> {

    public ActivationDateXmlDeserializer() {
        super(ActivationDate.kmipTag, ActivationDate.encodingType, OffsetDateTime.class, value -> ActivationDate.builder().value(value).build());
    }
}
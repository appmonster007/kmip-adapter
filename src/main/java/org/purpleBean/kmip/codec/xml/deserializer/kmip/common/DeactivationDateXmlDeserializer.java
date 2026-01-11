package org.purpleBean.kmip.codec.xml.deserializer.kmip.common;

import org.purpleBean.kmip.codec.xml.deserializer.AbstractKmipXmlDeserializer;
import org.purpleBean.kmip.common.DeactivationDate;

import java.time.OffsetDateTime;

public class DeactivationDateXmlDeserializer extends AbstractKmipXmlDeserializer<DeactivationDate, OffsetDateTime> {

    public DeactivationDateXmlDeserializer() {
        super(DeactivationDate.kmipTag, DeactivationDate.encodingType, OffsetDateTime.class, value -> DeactivationDate.builder().value(value).build());
    }
}
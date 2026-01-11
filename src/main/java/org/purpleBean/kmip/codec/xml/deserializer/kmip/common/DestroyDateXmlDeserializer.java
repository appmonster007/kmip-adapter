package org.purpleBean.kmip.codec.xml.deserializer.kmip.common;

import org.purpleBean.kmip.codec.xml.deserializer.AbstractKmipXmlDeserializer;
import org.purpleBean.kmip.common.DestroyDate;

import java.time.OffsetDateTime;

public class DestroyDateXmlDeserializer extends AbstractKmipXmlDeserializer<DestroyDate, OffsetDateTime> {

    public DestroyDateXmlDeserializer() {
        super(DestroyDate.kmipTag, DestroyDate.encodingType, OffsetDateTime.class, value -> DestroyDate.builder().value(value).build());
    }
}
package org.purpleBean.kmip.codec.xml.deserializer.kmip.common;

import org.purpleBean.kmip.codec.xml.deserializer.AbstractKmipXmlDeserializer;
import org.purpleBean.kmip.common.OriginalCreationDate;

import java.time.OffsetDateTime;

public class OriginalCreationDateXmlDeserializer extends AbstractKmipXmlDeserializer<OriginalCreationDate, OffsetDateTime> {

    public OriginalCreationDateXmlDeserializer() {
        super(OriginalCreationDate.kmipTag, OriginalCreationDate.encodingType, OffsetDateTime.class, value -> OriginalCreationDate.builder().value(value).build());
    }
}
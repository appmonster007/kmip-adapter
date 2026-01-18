package org.purpleBean.kmip.codec.xml.deserializer.model.core.type;

import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.type.OriginalCreationDate;

import java.time.OffsetDateTime;

public class OriginalCreationDateXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<OriginalCreationDate, OffsetDateTime> {

    public OriginalCreationDateXmlDeserializer() {
        super(OriginalCreationDate.kmipTag, OriginalCreationDate.encodingType, OffsetDateTime.class, value -> OriginalCreationDate.builder().value(value).build());
    }
}
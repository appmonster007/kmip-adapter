package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import org.purpleBean.kmip.codec.json.deserializer.AbstractKmipJsonDeserializer;
import org.purpleBean.kmip.common.OriginalCreationDate;

import java.time.OffsetDateTime;

public class OriginalCreationDateJsonDeserializer extends AbstractKmipJsonDeserializer<OriginalCreationDate, OffsetDateTime> {

    public OriginalCreationDateJsonDeserializer() {
        super(OriginalCreationDate.kmipTag, OriginalCreationDate.encodingType, OffsetDateTime.class, value -> OriginalCreationDate.builder().value(value).build());
    }
}
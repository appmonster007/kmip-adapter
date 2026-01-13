package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import org.purpleBean.kmip.codec.json.deserializer.kmip.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.OriginalCreationDate;

import java.time.OffsetDateTime;

public class OriginalCreationDateJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<OriginalCreationDate, OffsetDateTime> {

    public OriginalCreationDateJsonDeserializer() {
        super(OriginalCreationDate.kmipTag, OriginalCreationDate.encodingType, OffsetDateTime.class, value -> OriginalCreationDate.builder().value(value).build());
    }
}
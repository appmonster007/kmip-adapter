package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.model.core.type.OriginalCreationDate;

import java.time.OffsetDateTime;

public class OriginalCreationDateTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<OriginalCreationDate, OffsetDateTime> {

    public OriginalCreationDateTtlvDeserializer() {
        super(OriginalCreationDate.kmipTag, OriginalCreationDate.encodingType, OffsetDateTime.class, value -> OriginalCreationDate.builder().value(value).build());
    }
}
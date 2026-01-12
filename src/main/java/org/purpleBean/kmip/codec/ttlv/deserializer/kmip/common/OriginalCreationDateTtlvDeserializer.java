package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.deserializer.AbstractKmipTtlvDeserializer;
import org.purpleBean.kmip.common.OriginalCreationDate;

import java.time.OffsetDateTime;

public class OriginalCreationDateTtlvDeserializer extends AbstractKmipTtlvDeserializer<OriginalCreationDate, OffsetDateTime> {

    public OriginalCreationDateTtlvDeserializer() {
        super(OriginalCreationDate.kmipTag, OriginalCreationDate.encodingType, OffsetDateTime.class, value -> OriginalCreationDate.builder().value(value).build());
    }
}
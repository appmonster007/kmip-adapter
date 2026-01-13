package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.common.OriginalCreationDate;

import java.time.OffsetDateTime;

public class OriginalCreationDateTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<OriginalCreationDate, OffsetDateTime> {

    public OriginalCreationDateTtlvDeserializer() {
        super(OriginalCreationDate.kmipTag, OriginalCreationDate.encodingType, OffsetDateTime.class, value -> OriginalCreationDate.builder().value(value).build());
    }
}
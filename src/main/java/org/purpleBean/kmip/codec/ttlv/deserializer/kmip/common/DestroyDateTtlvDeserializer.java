package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.deserializer.AbstractKmipTtlvDeserializer;
import org.purpleBean.kmip.common.DestroyDate;

import java.time.OffsetDateTime;

public class DestroyDateTtlvDeserializer extends AbstractKmipTtlvDeserializer<DestroyDate, OffsetDateTime> {

    public DestroyDateTtlvDeserializer() {
        super(DestroyDate.kmipTag, DestroyDate.encodingType, OffsetDateTime.class, value -> DestroyDate.builder().value(value).build());
    }
}
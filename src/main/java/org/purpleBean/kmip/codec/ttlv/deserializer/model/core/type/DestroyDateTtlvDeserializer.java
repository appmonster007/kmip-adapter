package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.model.core.type.DestroyDate;

import java.time.OffsetDateTime;

public class DestroyDateTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<DestroyDate, OffsetDateTime> {

    public DestroyDateTtlvDeserializer() {
        super(DestroyDate.kmipTag, DestroyDate.encodingType, OffsetDateTime.class, value -> DestroyDate.builder().value(value).build());
    }
}
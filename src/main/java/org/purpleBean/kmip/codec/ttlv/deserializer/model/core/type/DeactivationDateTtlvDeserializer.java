package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.model.core.type.DeactivationDate;

import java.time.OffsetDateTime;

public class DeactivationDateTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<DeactivationDate, OffsetDateTime> {

    public DeactivationDateTtlvDeserializer() {
        super(DeactivationDate.kmipTag, DeactivationDate.encodingType, OffsetDateTime.class, value -> DeactivationDate.builder().value(value).build());
    }
}
package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.common.DeactivationDate;

import java.time.OffsetDateTime;

public class DeactivationDateTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<DeactivationDate, OffsetDateTime> {

    public DeactivationDateTtlvDeserializer() {
        super(DeactivationDate.kmipTag, DeactivationDate.encodingType, OffsetDateTime.class, value -> DeactivationDate.builder().value(value).build());
    }
}
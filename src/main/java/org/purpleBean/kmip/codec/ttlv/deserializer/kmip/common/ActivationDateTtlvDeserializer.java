package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.deserializer.AbstractKmipTtlvDeserializer;
import org.purpleBean.kmip.common.ActivationDate;

import java.time.OffsetDateTime;

public class ActivationDateTtlvDeserializer extends AbstractKmipTtlvDeserializer<ActivationDate, OffsetDateTime> {

    public ActivationDateTtlvDeserializer() {
        super(ActivationDate.kmipTag, ActivationDate.encodingType, OffsetDateTime.class, value -> ActivationDate.builder().value(value).build());
    }
}
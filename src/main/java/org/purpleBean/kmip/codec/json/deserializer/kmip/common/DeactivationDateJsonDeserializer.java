package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import org.purpleBean.kmip.codec.json.deserializer.AbstractKmipJsonDeserializer;
import org.purpleBean.kmip.common.DeactivationDate;

import java.time.OffsetDateTime;

public class DeactivationDateJsonDeserializer extends AbstractKmipJsonDeserializer<DeactivationDate, OffsetDateTime> {

    public DeactivationDateJsonDeserializer() {
        super(DeactivationDate.kmipTag, DeactivationDate.encodingType, OffsetDateTime.class, value -> DeactivationDate.builder().value(value).build());
    }
}
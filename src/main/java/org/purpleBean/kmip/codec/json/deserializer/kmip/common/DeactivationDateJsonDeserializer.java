package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import org.purpleBean.kmip.codec.json.deserializer.kmip.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.DeactivationDate;

import java.time.OffsetDateTime;

public class DeactivationDateJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<DeactivationDate, OffsetDateTime> {

    public DeactivationDateJsonDeserializer() {
        super(DeactivationDate.kmipTag, DeactivationDate.encodingType, OffsetDateTime.class, value -> DeactivationDate.builder().value(value).build());
    }
}
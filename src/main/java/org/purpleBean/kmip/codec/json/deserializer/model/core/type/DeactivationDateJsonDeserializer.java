package org.purpleBean.kmip.codec.json.deserializer.model.core.type;

import org.purpleBean.kmip.codec.json.deserializer.kmip.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.type.DeactivationDate;

import java.time.OffsetDateTime;

public class DeactivationDateJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<DeactivationDate, OffsetDateTime> {

    public DeactivationDateJsonDeserializer() {
        super(DeactivationDate.kmipTag, DeactivationDate.encodingType, OffsetDateTime.class, value -> DeactivationDate.builder().value(value).build());
    }
}
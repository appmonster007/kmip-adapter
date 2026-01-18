package org.purpleBean.kmip.codec.json.deserializer.model.core.type;

import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.type.DestroyDate;

import java.time.OffsetDateTime;

public class DestroyDateJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<DestroyDate, OffsetDateTime> {

    public DestroyDateJsonDeserializer() {
        super(DestroyDate.kmipTag, DestroyDate.encodingType, OffsetDateTime.class, value -> DestroyDate.builder().value(value).build());
    }
}
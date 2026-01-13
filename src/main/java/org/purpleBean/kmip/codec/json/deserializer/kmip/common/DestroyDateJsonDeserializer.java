package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import org.purpleBean.kmip.codec.json.deserializer.kmip.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.DestroyDate;

import java.time.OffsetDateTime;

public class DestroyDateJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<DestroyDate, OffsetDateTime> {

    public DestroyDateJsonDeserializer() {
        super(DestroyDate.kmipTag, DestroyDate.encodingType, OffsetDateTime.class, value -> DestroyDate.builder().value(value).build());
    }
}
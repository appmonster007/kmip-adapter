package org.purpleBean.kmip.codec.json.deserializer.model.core.type;

import org.purpleBean.kmip.codec.json.deserializer.kmip.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.type.ActivationDate;

import java.time.OffsetDateTime;

public class ActivationDateJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<ActivationDate, OffsetDateTime> {

    public ActivationDateJsonDeserializer() {
        super(ActivationDate.kmipTag, ActivationDate.encodingType, OffsetDateTime.class, value -> ActivationDate.builder().value(value).build());
    }
}
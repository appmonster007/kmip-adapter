package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import org.purpleBean.kmip.codec.json.deserializer.AbstractKmipJsonDeserializer;
import org.purpleBean.kmip.common.ActivationDate;

import java.time.OffsetDateTime;

public class ActivationDateJsonDeserializer extends AbstractKmipJsonDeserializer<ActivationDate, OffsetDateTime> {

    public ActivationDateJsonDeserializer() {
        super(ActivationDate.kmipTag, ActivationDate.encodingType, OffsetDateTime.class, value -> ActivationDate.builder().value(value).build());
    }
}
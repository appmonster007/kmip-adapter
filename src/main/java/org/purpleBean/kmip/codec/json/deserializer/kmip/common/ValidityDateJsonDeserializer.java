package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import org.purpleBean.kmip.codec.json.deserializer.AbstractKmipJsonDeserializer;
import org.purpleBean.kmip.common.ValidityDate;

import java.time.OffsetDateTime;

public class ValidityDateJsonDeserializer extends AbstractKmipJsonDeserializer<ValidityDate, OffsetDateTime> {

    public ValidityDateJsonDeserializer() {
        super(ValidityDate.kmipTag, ValidityDate.encodingType, OffsetDateTime.class, value -> ValidityDate.builder().value(value).build());
    }
}
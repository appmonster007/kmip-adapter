package org.purpleBean.kmip.codec.json.deserializer.model.core.type;

import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.type.ValidityDate;

import java.time.OffsetDateTime;

public class ValidityDateJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<ValidityDate, OffsetDateTime> {

    public ValidityDateJsonDeserializer() {
        super(ValidityDate.kmipTag, ValidityDate.encodingType, OffsetDateTime.class, value -> ValidityDate.builder().value(value).build());
    }
}
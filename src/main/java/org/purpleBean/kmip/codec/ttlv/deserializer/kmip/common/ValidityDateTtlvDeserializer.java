package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.common.ValidityDate;

import java.time.OffsetDateTime;

public class ValidityDateTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<ValidityDate, OffsetDateTime> {

    public ValidityDateTtlvDeserializer() {
        super(ValidityDate.kmipTag, ValidityDate.encodingType, OffsetDateTime.class, value -> ValidityDate.builder().value(value).build());
    }
}
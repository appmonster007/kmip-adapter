package org.purpleBean.kmip.codec.xml.deserializer.kmip.common;

import org.purpleBean.kmip.codec.xml.deserializer.AbstractKmipXmlDeserializer;
import org.purpleBean.kmip.common.ValidityDate;

import java.time.OffsetDateTime;

public class ValidityDateXmlDeserializer extends AbstractKmipXmlDeserializer<ValidityDate, OffsetDateTime> {

    public ValidityDateXmlDeserializer() {
        super(ValidityDate.kmipTag, ValidityDate.encodingType, OffsetDateTime.class, value -> ValidityDate.builder().value(value).build());
    }
}
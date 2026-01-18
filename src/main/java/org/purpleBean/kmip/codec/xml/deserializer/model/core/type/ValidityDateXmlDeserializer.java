package org.purpleBean.kmip.codec.xml.deserializer.model.core.type;

import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.type.ValidityDate;

import java.time.OffsetDateTime;

public class ValidityDateXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<ValidityDate, OffsetDateTime> {

    public ValidityDateXmlDeserializer() {
        super(ValidityDate.kmipTag, ValidityDate.encodingType, OffsetDateTime.class, value -> ValidityDate.builder().value(value).build());
    }
}
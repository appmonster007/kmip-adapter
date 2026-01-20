package org.purpleBean.kmip.codec.xml.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.enumeration.ValidityIndicator;

public class ValidityIndicatorXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<ValidityIndicator, String> {

    public ValidityIndicatorXmlDeserializer() {
        super(ValidityIndicator.kmipTag, ValidityIndicator.encodingType, String.class, value -> ValidityIndicator.fromName(value).inst());
    }
}
package org.purpleBean.kmip.codec.xml.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.xml.deserializer.kmip.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.common.enumeration.ValidityIndicator;

public class ValidityIndicatorXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<ValidityIndicator, String> {

    public ValidityIndicatorXmlDeserializer() {
        super(ValidityIndicator.kmipTag, ValidityIndicator.encodingType, String.class, value -> new ValidityIndicator(ValidityIndicator.fromName(value)));
    }
}
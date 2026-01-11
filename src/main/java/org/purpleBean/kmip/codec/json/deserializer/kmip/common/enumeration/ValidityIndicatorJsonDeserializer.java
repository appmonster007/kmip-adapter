package org.purpleBean.kmip.codec.json.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.deserializer.AbstractKmipJsonDeserializer;
import org.purpleBean.kmip.common.enumeration.ValidityIndicator;

public class ValidityIndicatorJsonDeserializer extends AbstractKmipJsonDeserializer<ValidityIndicator, String> {

    public ValidityIndicatorJsonDeserializer() {
        super(ValidityIndicator.kmipTag, ValidityIndicator.encodingType, String.class, value -> new ValidityIndicator(ValidityIndicator.fromName(value)));
    }
}
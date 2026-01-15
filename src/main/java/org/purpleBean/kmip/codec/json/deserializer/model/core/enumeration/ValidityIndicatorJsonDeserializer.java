package org.purpleBean.kmip.codec.json.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.json.deserializer.kmip.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.enumeration.ValidityIndicator;

public class ValidityIndicatorJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<ValidityIndicator, String> {

    public ValidityIndicatorJsonDeserializer() {
        super(ValidityIndicator.kmipTag, ValidityIndicator.encodingType, String.class, value -> new ValidityIndicator(ValidityIndicator.fromName(value)));
    }
}
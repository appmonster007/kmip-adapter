package org.purpleBean.kmip.codec.json.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.deserializer.AbstractKmipJsonDeserializer;
import org.purpleBean.kmip.common.enumeration.ValidationType;

public class ValidationTypeJsonDeserializer extends AbstractKmipJsonDeserializer<ValidationType, String> {

    public ValidationTypeJsonDeserializer() {
        super(ValidationType.kmipTag, ValidationType.encodingType, String.class, value -> new ValidationType(ValidationType.fromName(value)));
    }
}
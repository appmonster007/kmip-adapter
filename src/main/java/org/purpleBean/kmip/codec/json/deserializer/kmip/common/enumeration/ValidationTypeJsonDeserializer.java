package org.purpleBean.kmip.codec.json.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.deserializer.kmip.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.enumeration.ValidationType;

public class ValidationTypeJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<ValidationType, String> {

    public ValidationTypeJsonDeserializer() {
        super(ValidationType.kmipTag, ValidationType.encodingType, String.class, value -> new ValidationType(ValidationType.fromName(value)));
    }
}
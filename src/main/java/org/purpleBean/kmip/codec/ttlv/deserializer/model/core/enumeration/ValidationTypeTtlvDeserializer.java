package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.model.core.enumeration.ValidationType;

public class ValidationTypeTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<ValidationType, Integer> {

    public ValidationTypeTtlvDeserializer() {
        super(ValidationType.kmipTag, ValidationType.encodingType, Integer.class, value -> new ValidationType(ValidationType.fromValue(value)));
    }
}
package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.common.enumeration.ValidationAuthorityType;

public class ValidationAuthorityTypeTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<ValidationAuthorityType, Integer> {

    public ValidationAuthorityTypeTtlvDeserializer() {
        super(ValidationAuthorityType.kmipTag, ValidationAuthorityType.encodingType, Integer.class, value -> new ValidationAuthorityType(ValidationAuthorityType.fromValue(value)));
    }
}
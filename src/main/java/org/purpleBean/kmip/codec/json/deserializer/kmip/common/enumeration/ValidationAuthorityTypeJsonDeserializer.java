package org.purpleBean.kmip.codec.json.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.deserializer.kmip.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.enumeration.ValidationAuthorityType;

public class ValidationAuthorityTypeJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<ValidationAuthorityType, String> {

    public ValidationAuthorityTypeJsonDeserializer() {
        super(ValidationAuthorityType.kmipTag, ValidationAuthorityType.encodingType, String.class, value -> new ValidationAuthorityType(ValidationAuthorityType.fromName(value)));
    }
}
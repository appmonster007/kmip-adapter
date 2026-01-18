package org.purpleBean.kmip.codec.xml.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.enumeration.ValidationAuthorityType;

public class ValidationAuthorityTypeXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<ValidationAuthorityType, String> {

    public ValidationAuthorityTypeXmlDeserializer() {
        super(ValidationAuthorityType.kmipTag, ValidationAuthorityType.encodingType, String.class, value -> new ValidationAuthorityType(ValidationAuthorityType.fromName(value)));
    }
}
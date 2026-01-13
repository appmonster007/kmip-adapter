package org.purpleBean.kmip.codec.xml.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.xml.deserializer.kmip.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.common.enumeration.ValidationAuthorityType;

public class ValidationAuthorityTypeXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<ValidationAuthorityType, String> {

    public ValidationAuthorityTypeXmlDeserializer() {
        super(ValidationAuthorityType.kmipTag, ValidationAuthorityType.encodingType, String.class, value -> new ValidationAuthorityType(ValidationAuthorityType.fromName(value)));
    }
}
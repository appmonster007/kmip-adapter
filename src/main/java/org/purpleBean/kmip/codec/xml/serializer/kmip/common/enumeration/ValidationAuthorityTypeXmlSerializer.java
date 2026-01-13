package org.purpleBean.kmip.codec.xml.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.enumeration.ValidationAuthorityType;

public class ValidationAuthorityTypeXmlSerializer extends AbstractKmipDataTypeXmlSerializer<ValidationAuthorityType, String> {

    public ValidationAuthorityTypeXmlSerializer() {
        super(ValidationAuthorityType::getDescription);
    }
}
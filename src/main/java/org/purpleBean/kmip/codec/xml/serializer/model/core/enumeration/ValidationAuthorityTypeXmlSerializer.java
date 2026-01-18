package org.purpleBean.kmip.codec.xml.serializer.model.core.enumeration;

import org.purpleBean.kmip.codec.xml.serializer.api.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.model.core.enumeration.ValidationAuthorityType;

public class ValidationAuthorityTypeXmlSerializer extends AbstractKmipDataTypeXmlSerializer<ValidationAuthorityType, String> {

    public ValidationAuthorityTypeXmlSerializer() {
        super(ValidationAuthorityType::getDescription);
    }
}
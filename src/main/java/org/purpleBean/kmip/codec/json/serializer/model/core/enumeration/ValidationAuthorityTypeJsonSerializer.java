package org.purpleBean.kmip.codec.json.serializer.model.core.enumeration;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.model.core.enumeration.ValidationAuthorityType;

public class ValidationAuthorityTypeJsonSerializer extends AbstractKmipDataTypeJsonSerializer<ValidationAuthorityType, String> {

    public ValidationAuthorityTypeJsonSerializer() {
        super(ValidationAuthorityType::getDescription);
    }
}
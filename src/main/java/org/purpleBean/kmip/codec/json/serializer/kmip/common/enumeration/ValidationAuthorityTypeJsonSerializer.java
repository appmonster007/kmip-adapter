package org.purpleBean.kmip.codec.json.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.serializer.AbstractKmipJsonSerializer;
import org.purpleBean.kmip.common.enumeration.ValidationAuthorityType;

public class ValidationAuthorityTypeJsonSerializer extends AbstractKmipJsonSerializer<ValidationAuthorityType, String> {

    public ValidationAuthorityTypeJsonSerializer() {
        super(ValidationAuthorityType::getDescription);
    }
}
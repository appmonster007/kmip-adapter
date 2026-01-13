package org.purpleBean.kmip.codec.json.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.enumeration.ValidationAuthorityType;

public class ValidationAuthorityTypeJsonSerializer extends AbstractKmipDataTypeJsonSerializer<ValidationAuthorityType, String> {

    public ValidationAuthorityTypeJsonSerializer() {
        super(ValidationAuthorityType::getDescription);
    }
}
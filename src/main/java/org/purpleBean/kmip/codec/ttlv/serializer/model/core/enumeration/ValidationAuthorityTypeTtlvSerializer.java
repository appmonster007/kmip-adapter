package org.purpleBean.kmip.codec.ttlv.serializer.model.core.enumeration;

import org.purpleBean.kmip.codec.ttlv.serializer.api.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.model.core.enumeration.ValidationAuthorityType;

public class ValidationAuthorityTypeTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<ValidationAuthorityType, Integer> {

    public ValidationAuthorityTypeTtlvSerializer() {
        super(ValidationAuthorityType::getValue);
    }
}
package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.common.enumeration.ValidationAuthorityType;

public class ValidationAuthorityTypeTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<ValidationAuthorityType, Integer> {

    public ValidationAuthorityTypeTtlvSerializer() {
        super(ValidationAuthorityType::getValue);
    }
}
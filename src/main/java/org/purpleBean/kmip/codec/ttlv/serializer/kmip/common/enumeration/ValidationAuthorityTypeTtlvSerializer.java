package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.serializer.AbstractKmipTtlvSerializer;
import org.purpleBean.kmip.common.enumeration.ValidationAuthorityType;

public class ValidationAuthorityTypeTtlvSerializer extends AbstractKmipTtlvSerializer<ValidationAuthorityType, Integer> {

    public ValidationAuthorityTypeTtlvSerializer() {
        super(value -> value.getValue().getValue());
    }
}
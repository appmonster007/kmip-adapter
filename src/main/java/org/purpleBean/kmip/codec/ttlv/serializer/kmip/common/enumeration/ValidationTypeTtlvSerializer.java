package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.serializer.AbstractKmipTtlvSerializer;
import org.purpleBean.kmip.common.enumeration.ValidationType;

public class ValidationTypeTtlvSerializer extends AbstractKmipTtlvSerializer<ValidationType, Integer> {

    public ValidationTypeTtlvSerializer() {
        super(value -> value.getValue().getValue());
    }
}
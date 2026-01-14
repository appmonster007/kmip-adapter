package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.common.enumeration.ValidationType;

public class ValidationTypeTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<ValidationType, Integer> {

    public ValidationTypeTtlvSerializer() {
        super(ValidationType::getValue);
    }
}
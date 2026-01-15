package org.purpleBean.kmip.codec.ttlv.serializer.model.core.enumeration;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.model.core.enumeration.ValidationType;

public class ValidationTypeTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<ValidationType, Integer> {

    public ValidationTypeTtlvSerializer() {
        super(ValidationType::getValue);
    }
}
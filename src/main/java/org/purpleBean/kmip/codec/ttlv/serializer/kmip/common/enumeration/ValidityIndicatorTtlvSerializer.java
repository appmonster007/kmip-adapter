package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.serializer.AbstractKmipTtlvSerializer;
import org.purpleBean.kmip.common.enumeration.ValidityIndicator;

public class ValidityIndicatorTtlvSerializer extends AbstractKmipTtlvSerializer<ValidityIndicator, Integer> {

    public ValidityIndicatorTtlvSerializer() {
        super(value -> value.getValue().getValue());
    }
}
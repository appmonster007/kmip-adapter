package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.common.enumeration.ValidityIndicator;

public class ValidityIndicatorTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<ValidityIndicator, Integer> {

    public ValidityIndicatorTtlvSerializer() {
        super(ValidityIndicator::getValue);
    }
}
package org.purpleBean.kmip.codec.ttlv.serializer.model.core.enumeration;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.model.core.enumeration.ValidityIndicator;

public class ValidityIndicatorTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<ValidityIndicator, Integer> {

    public ValidityIndicatorTtlvSerializer() {
        super(ValidityIndicator::getValue);
    }
}
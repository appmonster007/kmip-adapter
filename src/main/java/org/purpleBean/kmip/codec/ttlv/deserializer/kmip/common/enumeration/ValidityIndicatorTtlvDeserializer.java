package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.common.enumeration.ValidityIndicator;

public class ValidityIndicatorTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<ValidityIndicator, Integer> {

    public ValidityIndicatorTtlvDeserializer() {
        super(ValidityIndicator.kmipTag, ValidityIndicator.encodingType, Integer.class, value -> new ValidityIndicator(ValidityIndicator.fromValue(value)));
    }
}
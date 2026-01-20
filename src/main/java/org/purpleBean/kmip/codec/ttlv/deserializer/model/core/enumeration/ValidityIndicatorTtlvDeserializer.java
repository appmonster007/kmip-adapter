package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.model.core.enumeration.ValidityIndicator;

public class ValidityIndicatorTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<ValidityIndicator, Integer> {

    public ValidityIndicatorTtlvDeserializer() {
        super(ValidityIndicator.kmipTag, ValidityIndicator.encodingType, Integer.class, value -> ValidityIndicator.fromValue(value).inst());
    }
}
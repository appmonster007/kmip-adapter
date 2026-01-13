package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.common.CriticalityIndicator;

public class CriticalityIndicatorTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<CriticalityIndicator, Boolean> {

    public CriticalityIndicatorTtlvDeserializer() {
        super(CriticalityIndicator.kmipTag, CriticalityIndicator.encodingType, Boolean.class, value -> CriticalityIndicator.builder().value(value).build());
    }
}
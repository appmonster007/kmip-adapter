package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.deserializer.AbstractKmipTtlvDeserializer;
import org.purpleBean.kmip.common.CriticalityIndicator;

public class CriticalityIndicatorTtlvDeserializer extends AbstractKmipTtlvDeserializer<CriticalityIndicator, Boolean> {

    public CriticalityIndicatorTtlvDeserializer() {
        super(CriticalityIndicator.kmipTag, CriticalityIndicator.encodingType, Boolean.class, value -> CriticalityIndicator.builder().value(value).build());
    }
}
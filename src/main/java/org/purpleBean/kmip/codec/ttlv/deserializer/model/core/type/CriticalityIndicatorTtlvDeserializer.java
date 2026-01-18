package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.model.core.type.CriticalityIndicator;

public class CriticalityIndicatorTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<CriticalityIndicator, Boolean> {

    public CriticalityIndicatorTtlvDeserializer() {
        super(CriticalityIndicator.kmipTag, CriticalityIndicator.encodingType, Boolean.class, value -> CriticalityIndicator.builder().value(value).build());
    }
}
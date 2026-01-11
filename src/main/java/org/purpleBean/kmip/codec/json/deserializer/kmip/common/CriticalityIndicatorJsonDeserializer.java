package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import org.purpleBean.kmip.codec.json.deserializer.AbstractKmipJsonDeserializer;
import org.purpleBean.kmip.common.CriticalityIndicator;

public class CriticalityIndicatorJsonDeserializer extends AbstractKmipJsonDeserializer<CriticalityIndicator, Boolean> {

    public CriticalityIndicatorJsonDeserializer() {
        super(CriticalityIndicator.kmipTag, CriticalityIndicator.encodingType, Boolean.class, value -> CriticalityIndicator.builder().value(value).build());
    }
}
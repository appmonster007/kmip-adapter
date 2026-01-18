package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.model.core.type.SplitKeyThreshold;

public class SplitKeyThresholdTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<SplitKeyThreshold, Integer> {

    public SplitKeyThresholdTtlvDeserializer() {
        super(SplitKeyThreshold.kmipTag, SplitKeyThreshold.encodingType, Integer.class, value -> SplitKeyThreshold.builder().value(value).build());
    }
}
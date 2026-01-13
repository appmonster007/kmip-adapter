package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.common.SplitKeyThreshold;

public class SplitKeyThresholdTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<SplitKeyThreshold, Integer> {

    public SplitKeyThresholdTtlvDeserializer() {
        super(SplitKeyThreshold.kmipTag, SplitKeyThreshold.encodingType, Integer.class, value -> SplitKeyThreshold.builder().value(value).build());
    }
}
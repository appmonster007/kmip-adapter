package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import org.purpleBean.kmip.codec.json.deserializer.kmip.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.SplitKeyThreshold;

public class SplitKeyThresholdJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<SplitKeyThreshold, Integer> {

    public SplitKeyThresholdJsonDeserializer() {
        super(SplitKeyThreshold.kmipTag, SplitKeyThreshold.encodingType, Integer.class, value -> SplitKeyThreshold.builder().value(value).build());
    }
}
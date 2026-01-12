package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.AbstractKmipJsonSerializer;
import org.purpleBean.kmip.common.SplitKeyThreshold;

public class SplitKeyThresholdJsonSerializer extends AbstractKmipJsonSerializer<SplitKeyThreshold, Integer> {

    public SplitKeyThresholdJsonSerializer() {
        super(SplitKeyThreshold::getValue);
    }
}
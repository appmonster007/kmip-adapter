package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.SplitKeyThreshold;

public class SplitKeyThresholdJsonSerializer extends AbstractKmipDataTypeJsonSerializer<SplitKeyThreshold, Integer> {

    public SplitKeyThresholdJsonSerializer() {
        super(SplitKeyThreshold::getValue);
    }
}
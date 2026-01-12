package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.AbstractKmipTtlvSerializer;
import org.purpleBean.kmip.common.SplitKeyThreshold;

public class SplitKeyThresholdTtlvSerializer extends AbstractKmipTtlvSerializer<SplitKeyThreshold, Integer> {

    public SplitKeyThresholdTtlvSerializer() {
        super(SplitKeyThreshold::getValue);
    }
}
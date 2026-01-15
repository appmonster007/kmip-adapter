package org.purpleBean.kmip.codec.json.serializer.model.core.type;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.model.core.type.SplitKeyThreshold;

public class SplitKeyThresholdJsonSerializer extends AbstractKmipDataTypeJsonSerializer<SplitKeyThreshold, Integer> {

    public SplitKeyThresholdJsonSerializer() {
        super(SplitKeyThreshold::getValue);
    }
}
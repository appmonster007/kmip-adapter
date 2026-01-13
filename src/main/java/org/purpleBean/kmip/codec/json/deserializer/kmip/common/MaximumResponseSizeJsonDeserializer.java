package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import org.purpleBean.kmip.codec.json.deserializer.kmip.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.MaximumResponseSize;

public class MaximumResponseSizeJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<MaximumResponseSize, Integer> {

    public MaximumResponseSizeJsonDeserializer() {
        super(MaximumResponseSize.kmipTag, MaximumResponseSize.encodingType, Integer.class, value -> MaximumResponseSize.builder().value(value).build());
    }
}
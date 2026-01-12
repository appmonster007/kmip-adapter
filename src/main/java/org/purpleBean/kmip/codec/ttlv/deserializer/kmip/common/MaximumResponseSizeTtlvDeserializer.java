package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.deserializer.AbstractKmipTtlvDeserializer;
import org.purpleBean.kmip.common.MaximumResponseSize;

public class MaximumResponseSizeTtlvDeserializer extends AbstractKmipTtlvDeserializer<MaximumResponseSize, Integer> {

    public MaximumResponseSizeTtlvDeserializer() {
        super(MaximumResponseSize.kmipTag, MaximumResponseSize.encodingType, Integer.class, value -> MaximumResponseSize.builder().value(value).build());
    }
}
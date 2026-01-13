package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.common.IterationCount;

public class IterationCountTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<IterationCount, Integer> {

    public IterationCountTtlvDeserializer() {
        super(IterationCount.kmipTag, IterationCount.encodingType, Integer.class, value -> IterationCount.builder().value(value).build());
    }
}
package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import org.purpleBean.kmip.codec.json.deserializer.AbstractKmipJsonDeserializer;
import org.purpleBean.kmip.common.IterationCount;

public class IterationCountJsonDeserializer extends AbstractKmipJsonDeserializer<IterationCount, Integer> {

    public IterationCountJsonDeserializer() {
        super(IterationCount.kmipTag, IterationCount.encodingType, Integer.class, value -> IterationCount.builder().value(value).build());
    }
}
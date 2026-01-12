package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.deserializer.AbstractKmipTtlvDeserializer;
import org.purpleBean.kmip.common.RandomIv;

public class RandomIvTtlvDeserializer extends AbstractKmipTtlvDeserializer<RandomIv, Boolean> {

    public RandomIvTtlvDeserializer() {
        super(RandomIv.kmipTag, RandomIv.encodingType, Boolean.class, value -> RandomIv.builder().value(value).build());
    }
}
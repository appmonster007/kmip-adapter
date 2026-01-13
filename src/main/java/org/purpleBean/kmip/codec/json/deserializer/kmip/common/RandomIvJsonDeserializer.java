package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import org.purpleBean.kmip.codec.json.deserializer.kmip.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.RandomIv;

public class RandomIvJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<RandomIv, Boolean> {

    public RandomIvJsonDeserializer() {
        super(RandomIv.kmipTag, RandomIv.encodingType, Boolean.class, value -> RandomIv.builder().value(value).build());
    }
}
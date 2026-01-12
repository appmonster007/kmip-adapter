package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.AbstractKmipJsonSerializer;
import org.purpleBean.kmip.common.RandomIv;

public class RandomIvJsonSerializer extends AbstractKmipJsonSerializer<RandomIv, Boolean> {

    public RandomIvJsonSerializer() {
        super(RandomIv::getValue);
    }
}
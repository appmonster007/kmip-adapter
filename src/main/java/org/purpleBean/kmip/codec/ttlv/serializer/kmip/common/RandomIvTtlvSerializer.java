package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.AbstractKmipTtlvSerializer;
import org.purpleBean.kmip.common.RandomIv;

public class RandomIvTtlvSerializer extends AbstractKmipTtlvSerializer<RandomIv, Boolean> {

    public RandomIvTtlvSerializer() {
        super(RandomIv::getValue);
    }
}
package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.common.RandomIv;

public class RandomIvTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<RandomIv, Boolean> {

    public RandomIvTtlvSerializer() {
        super(RandomIv::getValue);
    }
}
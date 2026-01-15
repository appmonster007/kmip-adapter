package org.purpleBean.kmip.codec.ttlv.serializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.model.core.type.RandomIv;

public class RandomIvTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<RandomIv, Boolean> {

    public RandomIvTtlvSerializer() {
        super(RandomIv::getValue);
    }
}
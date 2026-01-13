package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.RandomIv;

public class RandomIvJsonSerializer extends AbstractKmipDataTypeJsonSerializer<RandomIv, Boolean> {

    public RandomIvJsonSerializer() {
        super(RandomIv::getValue);
    }
}
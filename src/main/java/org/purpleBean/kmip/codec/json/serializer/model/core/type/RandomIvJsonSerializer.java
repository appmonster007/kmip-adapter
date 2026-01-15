package org.purpleBean.kmip.codec.json.serializer.model.core.type;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.model.core.type.RandomIv;

public class RandomIvJsonSerializer extends AbstractKmipDataTypeJsonSerializer<RandomIv, Boolean> {

    public RandomIvJsonSerializer() {
        super(RandomIv::getValue);
    }
}
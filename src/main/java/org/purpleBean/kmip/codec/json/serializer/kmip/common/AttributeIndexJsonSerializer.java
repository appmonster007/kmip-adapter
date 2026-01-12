package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.AbstractKmipJsonSerializer;
import org.purpleBean.kmip.common.AttributeIndex;

public class AttributeIndexJsonSerializer extends AbstractKmipJsonSerializer<AttributeIndex, Integer> {

    public AttributeIndexJsonSerializer() {
        super(AttributeIndex::getValue);
    }
}
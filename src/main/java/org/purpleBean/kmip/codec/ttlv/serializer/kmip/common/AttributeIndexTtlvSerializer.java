package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.AbstractKmipTtlvSerializer;
import org.purpleBean.kmip.common.AttributeIndex;

public class AttributeIndexTtlvSerializer extends AbstractKmipTtlvSerializer<AttributeIndex, Integer> {

    public AttributeIndexTtlvSerializer() {
        super(AttributeIndex::getValue);
    }
}
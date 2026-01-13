package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.common.AttributeIndex;

public class AttributeIndexTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<AttributeIndex, Integer> {

    public AttributeIndexTtlvSerializer() {
        super(AttributeIndex::getValue);
    }
}
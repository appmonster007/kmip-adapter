package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.AttributeIndex;

public class AttributeIndexJsonSerializer extends AbstractKmipDataTypeJsonSerializer<AttributeIndex, Integer> {

    public AttributeIndexJsonSerializer() {
        super(AttributeIndex::getValue);
    }
}
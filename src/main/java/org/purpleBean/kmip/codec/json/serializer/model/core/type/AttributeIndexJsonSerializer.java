package org.purpleBean.kmip.codec.json.serializer.model.core.type;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.model.core.type.AttributeIndex;

public class AttributeIndexJsonSerializer extends AbstractKmipDataTypeJsonSerializer<AttributeIndex, Integer> {

    public AttributeIndexJsonSerializer() {
        super(AttributeIndex::getValue);
    }
}
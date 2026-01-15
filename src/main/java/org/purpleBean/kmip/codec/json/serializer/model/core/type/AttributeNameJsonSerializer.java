package org.purpleBean.kmip.codec.json.serializer.model.core.type;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.model.core.type.AttributeName;

public class AttributeNameJsonSerializer extends AbstractKmipDataTypeJsonSerializer<AttributeName, String> {

    public AttributeNameJsonSerializer() {
        super(AttributeName::getValue);
    }
}
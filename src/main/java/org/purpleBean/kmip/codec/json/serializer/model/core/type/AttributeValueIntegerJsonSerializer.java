package org.purpleBean.kmip.codec.json.serializer.model.core.type;

import org.purpleBean.kmip.codec.json.serializer.api.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.model.core.type.AttributeValueInteger;

public class AttributeValueIntegerJsonSerializer extends AbstractKmipDataTypeJsonSerializer<AttributeValueInteger, Integer> {

    public AttributeValueIntegerJsonSerializer() {
        super(AttributeValueInteger::getValue);
    }
}
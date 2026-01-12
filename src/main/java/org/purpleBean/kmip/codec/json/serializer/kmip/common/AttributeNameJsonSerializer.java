package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.AbstractKmipJsonSerializer;
import org.purpleBean.kmip.common.AttributeName;

public class AttributeNameJsonSerializer extends AbstractKmipJsonSerializer<AttributeName, String> {

    public AttributeNameJsonSerializer() {
        super(AttributeName::getValue);
    }
}
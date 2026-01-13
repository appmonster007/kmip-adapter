package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.AttributeName;

public class AttributeNameJsonSerializer extends AbstractKmipDataTypeJsonSerializer<AttributeName, String> {

    public AttributeNameJsonSerializer() {
        super(AttributeName::getValue);
    }
}
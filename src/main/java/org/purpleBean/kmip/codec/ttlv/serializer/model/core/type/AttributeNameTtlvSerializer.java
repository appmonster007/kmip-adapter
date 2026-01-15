package org.purpleBean.kmip.codec.ttlv.serializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.model.core.type.AttributeName;

public class AttributeNameTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<AttributeName, String> {

    public AttributeNameTtlvSerializer() {
        super(AttributeName::getValue);
    }
}
package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.AbstractKmipTtlvSerializer;
import org.purpleBean.kmip.common.AttributeName;

public class AttributeNameTtlvSerializer extends AbstractKmipTtlvSerializer<AttributeName, String> {

    public AttributeNameTtlvSerializer() {
        super(AttributeName::getValue);
    }
}
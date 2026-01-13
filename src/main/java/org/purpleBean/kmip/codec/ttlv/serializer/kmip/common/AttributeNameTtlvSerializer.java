package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.common.AttributeName;

public class AttributeNameTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<AttributeName, String> {

    public AttributeNameTtlvSerializer() {
        super(AttributeName::getValue);
    }
}
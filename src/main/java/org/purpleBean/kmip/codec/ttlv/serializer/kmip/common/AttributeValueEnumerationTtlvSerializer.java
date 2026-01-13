package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.common.AttributeValueEnumeration;

public class AttributeValueEnumerationTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<AttributeValueEnumeration, Integer> {

    public AttributeValueEnumerationTtlvSerializer() {
        super(AttributeValueEnumeration::getValue);
    }
}
package org.purpleBean.kmip.codec.ttlv.serializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.serializer.api.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.model.core.type.AttributeValueEnumeration;

public class AttributeValueEnumerationTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<AttributeValueEnumeration, Integer> {

    public AttributeValueEnumerationTtlvSerializer() {
        super(AttributeValueEnumeration::getIntValue);
    }
}
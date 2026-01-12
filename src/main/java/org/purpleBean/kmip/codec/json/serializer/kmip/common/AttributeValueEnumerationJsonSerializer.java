package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.AbstractKmipJsonSerializer;
import org.purpleBean.kmip.common.AttributeValueEnumeration;

public class AttributeValueEnumerationJsonSerializer extends AbstractKmipJsonSerializer<AttributeValueEnumeration, Integer> {

    public AttributeValueEnumerationJsonSerializer() {
        super(AttributeValueEnumeration::getValue);
    }
}
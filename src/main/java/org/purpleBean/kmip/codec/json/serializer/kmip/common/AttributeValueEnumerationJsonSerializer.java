package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.AttributeValueEnumeration;

public class AttributeValueEnumerationJsonSerializer extends AbstractKmipDataTypeJsonSerializer<AttributeValueEnumeration, Integer> {

    public AttributeValueEnumerationJsonSerializer() {
        super(AttributeValueEnumeration::getValue);
    }
}
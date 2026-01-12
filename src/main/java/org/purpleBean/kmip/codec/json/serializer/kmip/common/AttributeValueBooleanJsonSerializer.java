package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.AbstractKmipJsonSerializer;
import org.purpleBean.kmip.common.AttributeValueBoolean;

public class AttributeValueBooleanJsonSerializer extends AbstractKmipJsonSerializer<AttributeValueBoolean, Boolean> {

    public AttributeValueBooleanJsonSerializer() {
        super(AttributeValueBoolean::getValue);
    }
}
package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.AttributeValueBoolean;

public class AttributeValueBooleanJsonSerializer extends AbstractKmipDataTypeJsonSerializer<AttributeValueBoolean, Boolean> {

    public AttributeValueBooleanJsonSerializer() {
        super(AttributeValueBoolean::getValue);
    }
}
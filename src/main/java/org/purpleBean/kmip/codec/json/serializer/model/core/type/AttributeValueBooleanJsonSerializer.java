package org.purpleBean.kmip.codec.json.serializer.model.core.type;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.model.core.type.AttributeValueBoolean;

public class AttributeValueBooleanJsonSerializer extends AbstractKmipDataTypeJsonSerializer<AttributeValueBoolean, Boolean> {

    public AttributeValueBooleanJsonSerializer() {
        super(AttributeValueBoolean::getValue);
    }
}
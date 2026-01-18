package org.purpleBean.kmip.codec.json.deserializer.model.core.type;

import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.type.AttributeValueBoolean;

public class AttributeValueBooleanJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<AttributeValueBoolean, Boolean> {

    public AttributeValueBooleanJsonDeserializer() {
        super(AttributeValueBoolean.kmipTag, AttributeValueBoolean.encodingType, Boolean.class, value -> AttributeValueBoolean.builder().value(value).build());
    }
}
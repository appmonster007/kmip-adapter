package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.model.core.type.AttributeValueBoolean;

public class AttributeValueBooleanTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<AttributeValueBoolean, Boolean> {

    public AttributeValueBooleanTtlvDeserializer() {
        super(AttributeValueBoolean.kmipTag, AttributeValueBoolean.encodingType, Boolean.class, value -> AttributeValueBoolean.builder().value(value).build());
    }
}
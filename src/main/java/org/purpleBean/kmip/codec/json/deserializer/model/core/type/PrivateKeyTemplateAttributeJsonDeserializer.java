package org.purpleBean.kmip.codec.json.deserializer.model.core.type;

import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.type.PrivateKeyTemplateAttribute;

public class PrivateKeyTemplateAttributeJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<PrivateKeyTemplateAttribute, String> {

    public PrivateKeyTemplateAttributeJsonDeserializer() {
        super(PrivateKeyTemplateAttribute.kmipTag, PrivateKeyTemplateAttribute.encodingType, String.class, value -> PrivateKeyTemplateAttribute.builder().value(value).build());
    }
}
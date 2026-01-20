package org.purpleBean.kmip.codec.json.deserializer.model.core.type;

import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.type.PublicKeyTemplateAttribute;

public class PublicKeyTemplateAttributeJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<PublicKeyTemplateAttribute, String> {

    public PublicKeyTemplateAttributeJsonDeserializer() {
        super(PublicKeyTemplateAttribute.kmipTag, PublicKeyTemplateAttribute.encodingType, String.class, value -> PublicKeyTemplateAttribute.builder().value(value).build());
    }
}
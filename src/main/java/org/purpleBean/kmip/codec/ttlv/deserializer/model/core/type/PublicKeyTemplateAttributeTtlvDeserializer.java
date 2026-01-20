package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.model.core.type.PublicKeyTemplateAttribute;

public class PublicKeyTemplateAttributeTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<PublicKeyTemplateAttribute, String> {

    public PublicKeyTemplateAttributeTtlvDeserializer() {
        super(PublicKeyTemplateAttribute.kmipTag, PublicKeyTemplateAttribute.encodingType, String.class, value -> PublicKeyTemplateAttribute.builder().value(value).build());
    }
}
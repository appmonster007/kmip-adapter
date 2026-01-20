package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.model.core.type.PrivateKeyTemplateAttribute;

public class PrivateKeyTemplateAttributeTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<PrivateKeyTemplateAttribute, String> {

    public PrivateKeyTemplateAttributeTtlvDeserializer() {
        super(PrivateKeyTemplateAttribute.kmipTag, PrivateKeyTemplateAttribute.encodingType, String.class, value -> PrivateKeyTemplateAttribute.builder().value(value).build());
    }
}
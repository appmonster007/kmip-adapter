package org.purpleBean.kmip.codec.xml.deserializer.model.core.type;

import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.type.PrivateKeyTemplateAttribute;

public class PrivateKeyTemplateAttributeXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<PrivateKeyTemplateAttribute, String> {

    public PrivateKeyTemplateAttributeXmlDeserializer() {
        super(PrivateKeyTemplateAttribute.kmipTag, PrivateKeyTemplateAttribute.encodingType, String.class, value -> PrivateKeyTemplateAttribute.builder().value(value).build());
    }
}
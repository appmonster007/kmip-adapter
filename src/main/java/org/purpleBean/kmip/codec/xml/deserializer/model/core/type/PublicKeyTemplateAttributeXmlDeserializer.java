package org.purpleBean.kmip.codec.xml.deserializer.model.core.type;

import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.type.PublicKeyTemplateAttribute;

public class PublicKeyTemplateAttributeXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<PublicKeyTemplateAttribute, String> {

    public PublicKeyTemplateAttributeXmlDeserializer() {
        super(PublicKeyTemplateAttribute.kmipTag, PublicKeyTemplateAttribute.encodingType, String.class, value -> PublicKeyTemplateAttribute.builder().value(value).build());
    }
}
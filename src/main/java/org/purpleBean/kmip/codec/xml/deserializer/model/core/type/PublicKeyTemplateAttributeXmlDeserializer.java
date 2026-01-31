package org.purpleBean.kmip.codec.xml.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.type.PublicKeyTemplateAttribute;

import java.io.IOException;

public class PublicKeyTemplateAttributeXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<PublicKeyTemplateAttribute, PublicKeyTemplateAttribute.PublicKeyTemplateAttributeBuilder> {

    public PublicKeyTemplateAttributeXmlDeserializer() {
        super(PublicKeyTemplateAttribute.kmipTag, PublicKeyTemplateAttribute.encodingType);
    }

    @Override
    protected PublicKeyTemplateAttribute.PublicKeyTemplateAttributeBuilder createBuilder() {
        return PublicKeyTemplateAttribute.builder();
    }

    @Override
    protected void setValue(PublicKeyTemplateAttribute.PublicKeyTemplateAttributeBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(ctxt.readValue(p, String.class));
    }

    @Override
    protected PublicKeyTemplateAttribute build(PublicKeyTemplateAttribute.PublicKeyTemplateAttributeBuilder builder) {
        return builder.build();
    }
}
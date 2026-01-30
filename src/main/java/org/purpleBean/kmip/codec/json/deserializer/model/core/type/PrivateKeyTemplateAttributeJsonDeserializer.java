package org.purpleBean.kmip.codec.json.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.type.PrivateKeyTemplateAttribute;

import java.io.IOException;

public class PrivateKeyTemplateAttributeJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<PrivateKeyTemplateAttribute, PrivateKeyTemplateAttribute.PrivateKeyTemplateAttributeBuilder> {

    public PrivateKeyTemplateAttributeJsonDeserializer() {
        super(PrivateKeyTemplateAttribute.kmipTag, PrivateKeyTemplateAttribute.encodingType);
    }

    @Override
    protected PrivateKeyTemplateAttribute.PrivateKeyTemplateAttributeBuilder createBuilder() {
        return PrivateKeyTemplateAttribute.builder();
    }

    @Override
    protected void setValue(PrivateKeyTemplateAttribute.PrivateKeyTemplateAttributeBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(ctxt.readValue(p, String.class));
    }

    @Override
    protected PrivateKeyTemplateAttribute build(PrivateKeyTemplateAttribute.PrivateKeyTemplateAttributeBuilder builder) {
        return builder.build();
    }
}

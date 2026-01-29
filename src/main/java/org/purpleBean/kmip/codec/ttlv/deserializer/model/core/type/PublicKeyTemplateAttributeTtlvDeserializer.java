package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.PublicKeyTemplateAttribute;

import java.io.IOException;
import java.nio.ByteBuffer;

public class PublicKeyTemplateAttributeTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<PublicKeyTemplateAttribute, PublicKeyTemplateAttribute.PublicKeyTemplateAttributeBuilder> {

    public PublicKeyTemplateAttributeTtlvDeserializer() {
        super(PublicKeyTemplateAttribute.kmipTag, PublicKeyTemplateAttribute.encodingType);
    }

    @Override
    protected PublicKeyTemplateAttribute.PublicKeyTemplateAttributeBuilder createBuilder() {
        return PublicKeyTemplateAttribute.builder();
    }

    @Override
    protected void setValue(PublicKeyTemplateAttribute.PublicKeyTemplateAttributeBuilder builder, byte[] tagBytes, byte type, ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
        builder.value(mapper.readValue(byteBuffer, String.class));
    }

    @Override
    protected PublicKeyTemplateAttribute build(PublicKeyTemplateAttribute.PublicKeyTemplateAttributeBuilder builder) {
        return builder.build();
    }
}

package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.PrivateKeyTemplateAttribute;

import java.io.IOException;
import java.nio.ByteBuffer;

public class PrivateKeyTemplateAttributeTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<PrivateKeyTemplateAttribute, PrivateKeyTemplateAttribute.PrivateKeyTemplateAttributeBuilder> {

    public PrivateKeyTemplateAttributeTtlvDeserializer() {
        super(PrivateKeyTemplateAttribute.kmipTag, PrivateKeyTemplateAttribute.encodingType);
    }

    @Override
    protected PrivateKeyTemplateAttribute.PrivateKeyTemplateAttributeBuilder createBuilder() {
        return PrivateKeyTemplateAttribute.builder();
    }

    @Override
    protected void setValue(PrivateKeyTemplateAttribute.PrivateKeyTemplateAttributeBuilder builder, byte[] tagBytes, byte type, ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
        builder.value(mapper.readValue(byteBuffer, String.class));
    }

    @Override
    protected PrivateKeyTemplateAttribute build(PrivateKeyTemplateAttribute.PrivateKeyTemplateAttributeBuilder builder) {
        return builder.build();
    }
}
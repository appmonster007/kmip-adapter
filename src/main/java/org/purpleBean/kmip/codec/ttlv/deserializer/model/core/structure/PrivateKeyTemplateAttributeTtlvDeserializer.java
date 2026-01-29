package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.structure;

import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.structure.Attribute;
import org.purpleBean.kmip.model.core.structure.Name;
import org.purpleBean.kmip.model.core.structure.PrivateKeyTemplateAttribute;

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
    protected void setValue(PrivateKeyTemplateAttribute.PrivateKeyTemplateAttributeBuilder builder, byte[] tagBytes, ByteBuffer p, TtlvMapper mapper) throws IOException {
        KmipTag.Value nodeTag = KmipTag.fromBytes(tagBytes);
        switch (nodeTag) {
            case KmipTag.Standard.NAME -> builder.name(mapper.readValue(p, Name.class));
            case KmipTag.Standard.ATTRIBUTE -> builder.attribute(mapper.readValue(p, Attribute.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected PrivateKeyTemplateAttribute build(PrivateKeyTemplateAttribute.PrivateKeyTemplateAttributeBuilder builder) {
        return builder.build();
    }
}
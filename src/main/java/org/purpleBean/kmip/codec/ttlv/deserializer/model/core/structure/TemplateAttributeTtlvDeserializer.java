package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.structure;

import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.structure.Attribute;
import org.purpleBean.kmip.model.core.structure.Name;
import org.purpleBean.kmip.model.core.structure.TemplateAttribute;

import java.io.IOException;
import java.nio.ByteBuffer;

public class TemplateAttributeTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<TemplateAttribute, TemplateAttribute.TemplateAttributeBuilder> {

    public TemplateAttributeTtlvDeserializer() {
        super(TemplateAttribute.kmipTag, TemplateAttribute.encodingType);
    }

    @Override
    protected TemplateAttribute.TemplateAttributeBuilder createBuilder() {
        return TemplateAttribute.builder();
    }

    @Override
    protected void setValue(TemplateAttribute.TemplateAttributeBuilder builder, byte[] tagBytes, ByteBuffer p, TtlvMapper mapper) throws IOException {
        KmipTag.Value nodeTag = KmipTag.fromBytes(tagBytes);
        switch (nodeTag) {
            case KmipTag.Standard.NAME -> builder.name(mapper.readValue(p, Name.class));
            case KmipTag.Standard.ATTRIBUTE -> builder.attribute(mapper.readValue(p, Attribute.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected TemplateAttribute build(TemplateAttribute.TemplateAttributeBuilder builder) {
        return builder.build();
    }
}
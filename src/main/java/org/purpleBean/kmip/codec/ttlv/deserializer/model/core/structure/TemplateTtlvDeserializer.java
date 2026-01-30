package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.structure;

import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.structure.Attribute;
import org.purpleBean.kmip.model.core.structure.Template;

import java.io.IOException;
import java.nio.ByteBuffer;

public class TemplateTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<Template, Template.TemplateBuilder> {

    public TemplateTtlvDeserializer() {
        super(Template.kmipTag, Template.encodingType);
    }

    @Override
    protected Template.TemplateBuilder createBuilder() {
        return Template.builder();
    }

    @Override
    protected void setValue(Template.TemplateBuilder builder, byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
        KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
        switch (nodeTag) {
            case KmipTag.Standard.ATTRIBUTE -> builder.attribute(mapper.readValue(p, Attribute.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected Template build(Template.TemplateBuilder builder) {
        return builder.build();
    }
}
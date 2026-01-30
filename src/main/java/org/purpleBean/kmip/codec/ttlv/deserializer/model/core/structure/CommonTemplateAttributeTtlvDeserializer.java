package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.structure;

import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.structure.Attribute;
import org.purpleBean.kmip.model.core.structure.CommonTemplateAttribute;
import org.purpleBean.kmip.model.core.structure.Name;

import java.io.IOException;
import java.nio.ByteBuffer;

public class CommonTemplateAttributeTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<CommonTemplateAttribute, CommonTemplateAttribute.CommonTemplateAttributeBuilder> {

    public CommonTemplateAttributeTtlvDeserializer() {
        super(CommonTemplateAttribute.kmipTag, CommonTemplateAttribute.encodingType);
    }

    @Override
    protected CommonTemplateAttribute.CommonTemplateAttributeBuilder createBuilder() {
        return CommonTemplateAttribute.builder();
    }

    @Override
    protected void setValue(CommonTemplateAttribute.CommonTemplateAttributeBuilder builder, byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
        KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
        switch (nodeTag) {
            case KmipTag.Standard.NAME -> builder.name(mapper.readValue(p, Name.class));
            case KmipTag.Standard.ATTRIBUTE -> builder.attribute(mapper.readValue(p, Attribute.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected CommonTemplateAttribute build(CommonTemplateAttribute.CommonTemplateAttributeBuilder builder) {
        return builder.build();
    }
}
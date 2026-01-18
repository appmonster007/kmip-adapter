package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.structure;

import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipStructureTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.structure.Attribute;
import org.purpleBean.kmip.model.core.structure.CommonTemplateAttribute;
import org.purpleBean.kmip.model.core.structure.Name;

import java.io.IOException;
import java.nio.ByteBuffer;

public class CommonTemplateAttributeTtlvDeserializer extends AbstractKmipStructureTtlvDeserializer<CommonTemplateAttribute, CommonTemplateAttribute.CommonTemplateAttributeBuilder> {

    public CommonTemplateAttributeTtlvDeserializer() {
        super(CommonTemplateAttribute.kmipTag);
    }

    @Override
    protected CommonTemplateAttribute.CommonTemplateAttributeBuilder createBuilder() {
        return CommonTemplateAttribute.builder();
    }

    @Override
    protected void setValue(CommonTemplateAttribute.CommonTemplateAttributeBuilder builder, KmipTag.Value nodeTag, ByteBuffer p, TtlvMapper mapper) throws IOException {
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

    @Override
    protected EncodingType getEncodingType() {
        return CommonTemplateAttribute.encodingType;
    }
}
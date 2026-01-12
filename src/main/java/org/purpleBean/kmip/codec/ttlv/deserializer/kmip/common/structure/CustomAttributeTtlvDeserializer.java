package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common.structure;

import org.purpleBean.kmip.AttributeValue;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.AbstractKmipStructureTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.common.AttributeName;
import org.purpleBean.kmip.common.structure.CustomAttribute;

import java.io.IOException;
import java.nio.ByteBuffer;

public class CustomAttributeTtlvDeserializer extends AbstractKmipStructureTtlvDeserializer<CustomAttribute, CustomAttribute.CustomAttributeBuilder> {

    public CustomAttributeTtlvDeserializer() {
        super(CustomAttribute.kmipTag);
    }

    @Override
    protected CustomAttribute.CustomAttributeBuilder createBuilder() {
        return CustomAttribute.builder();
    }

    @Override
    protected void setValue(CustomAttribute.CustomAttributeBuilder builder, KmipTag.Value nodeTag, ByteBuffer p, TtlvMapper mapper) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.ATTRIBUTE_NAME -> builder.attributeName(mapper.readValue(p, AttributeName.class));
            case KmipTag.Standard.ATTRIBUTE_VALUE -> builder.attributeValue(mapper.readValue(p, AttributeValue.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected CustomAttribute build(CustomAttribute.CustomAttributeBuilder builder) {
        return builder.build();
    }

    @Override
    protected EncodingType getEncodingType() {
        return CustomAttribute.encodingType;
    }
}
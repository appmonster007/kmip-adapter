package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.structure;

import org.purpleBean.kmip.api.AttributeValue;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.AbstractKmipStructureTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.structure.Attribute;
import org.purpleBean.kmip.model.core.type.AttributeIndex;
import org.purpleBean.kmip.model.core.type.AttributeName;

import java.io.IOException;
import java.nio.ByteBuffer;

public class AttributeTtlvDeserializer extends AbstractKmipStructureTtlvDeserializer<Attribute, Attribute.AttributeBuilder> {

    public AttributeTtlvDeserializer() {
        super(Attribute.kmipTag);
    }

    @Override
    protected Attribute.AttributeBuilder createBuilder() {
        return Attribute.builder();
    }

    @Override
    protected void setValue(Attribute.AttributeBuilder builder, KmipTag.Value nodeTag, ByteBuffer p, TtlvMapper mapper) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.ATTRIBUTE_NAME -> builder.attributeName(mapper.readValue(p, AttributeName.class));
            case KmipTag.Standard.ATTRIBUTE_INDEX -> builder.attributeIndex(mapper.readValue(p, AttributeIndex.class));
            case KmipTag.Standard.ATTRIBUTE_VALUE -> builder.attributeValue(mapper.readValue(p, AttributeValue.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected Attribute build(Attribute.AttributeBuilder builder) {
        return builder.build();
    }

    @Override
    protected EncodingType getEncodingType() {
        return Attribute.encodingType;
    }
}
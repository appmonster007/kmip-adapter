package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.structure;

import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.structure.Attribute;
import org.purpleBean.kmip.model.core.type.AttributeIndex;
import org.purpleBean.kmip.model.core.type.AttributeName;
import org.purpleBean.kmip.model.core.type.AttributeValue;

import java.io.IOException;
import java.nio.ByteBuffer;

public class AttributeTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<Attribute, Attribute.AttributeBuilder> {

    public AttributeTtlvDeserializer() {
        super(Attribute.kmipTag, Attribute.encodingType);
    }

    @Override
    protected Attribute.AttributeBuilder createBuilder() {
        return Attribute.builder();
    }

    @Override
    protected void setValue(Attribute.AttributeBuilder builder, byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
        KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
        switch (nodeTag) {
            case KmipTag.Standard.ATTRIBUTE_NAME -> {
                AttributeName attributeName = mapper.readValue(p, AttributeName.class);
                mapper.setAttribute("attributeName", attributeName.getValue());
                builder.attributeName(attributeName);
            }
            case KmipTag.Standard.ATTRIBUTE_INDEX -> builder.attributeIndex(mapper.readValue(p, AttributeIndex.class));
            case KmipTag.Standard.ATTRIBUTE_VALUE -> builder.attributeValue(mapper.readValue(p, AttributeValue.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected Attribute build(Attribute.AttributeBuilder builder) {
        return builder.build();
    }
}
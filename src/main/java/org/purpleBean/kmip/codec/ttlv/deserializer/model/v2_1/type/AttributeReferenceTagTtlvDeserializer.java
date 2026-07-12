package org.purpleBean.kmip.codec.ttlv.deserializer.model.v2_1.type;

import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.v2_1.type.AttributeReferenceTag;

import java.io.IOException;
import java.nio.ByteBuffer;

public class AttributeReferenceTagTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<AttributeReferenceTag, AttributeReferenceTag.AttributeReferenceTagBuilder> {

    public AttributeReferenceTagTtlvDeserializer() {
        super(AttributeReferenceTag.kmipTag, AttributeReferenceTag.encodingType);
    }

    @Override
    protected AttributeReferenceTag.AttributeReferenceTagBuilder createBuilder() {
        return AttributeReferenceTag.builder();
    }

    @Override
    protected void setValue(AttributeReferenceTag.AttributeReferenceTagBuilder builder, byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
        int tagValue = p.getInt();
        KmipTag.Value kmipTagValue = KmipTag.fromValue(tagValue);
        builder.tagDescription(kmipTagValue.getDescription());
    }

    @Override
    protected AttributeReferenceTag build(AttributeReferenceTag.AttributeReferenceTagBuilder builder) {
        return builder.build();
    }
}

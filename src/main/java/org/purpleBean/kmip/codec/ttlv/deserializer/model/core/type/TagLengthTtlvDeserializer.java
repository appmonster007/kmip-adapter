package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.TagLength;

import java.io.IOException;
import java.nio.ByteBuffer;

public class TagLengthTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<TagLength, TagLength.TagLengthBuilder> {

    public TagLengthTtlvDeserializer() {
        super(TagLength.kmipTag, TagLength.encodingType);
    }

    @Override
    protected TagLength.TagLengthBuilder createBuilder() {
        return TagLength.builder();
    }

    @Override
    protected void setValue(TagLength.TagLengthBuilder builder, byte[] tagBytes, ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
        builder.value(mapper.readValue(byteBuffer, Integer.class));
    }

    @Override
    protected TagLength build(TagLength.TagLengthBuilder builder) {
        return builder.build();
    }
}

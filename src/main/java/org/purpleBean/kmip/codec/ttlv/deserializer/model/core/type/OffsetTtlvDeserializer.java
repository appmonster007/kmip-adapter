package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.Offset;

import java.io.IOException;
import java.nio.ByteBuffer;

public class OffsetTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<Offset, Offset.OffsetBuilder> {

    public OffsetTtlvDeserializer() {
        super(Offset.kmipTag, Offset.encodingType);
    }

    @Override
    protected Offset.OffsetBuilder createBuilder() {
        return Offset.builder();
    }

    @Override
    protected void setValue(Offset.OffsetBuilder builder, byte[] tagBytes, ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
        builder.value(mapper.readValue(byteBuffer, Integer.class));
    }

    @Override
    protected Offset build(Offset.OffsetBuilder builder) {
        return builder.build();
    }
}

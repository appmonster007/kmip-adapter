package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.OriginalCreationDate;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.time.OffsetDateTime;

public class OriginalCreationDateTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<OriginalCreationDate, OriginalCreationDate.OriginalCreationDateBuilder> {

    public OriginalCreationDateTtlvDeserializer() {
        super(OriginalCreationDate.kmipTag, OriginalCreationDate.encodingType);
    }

    @Override
    protected OriginalCreationDate.OriginalCreationDateBuilder createBuilder() {
        return OriginalCreationDate.builder();
    }

    @Override
    protected void setValue(OriginalCreationDate.OriginalCreationDateBuilder builder, byte[] tag, byte type, ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
        builder.value(mapper.readValue(byteBuffer, OffsetDateTime.class));
    }

    @Override
    protected OriginalCreationDate build(OriginalCreationDate.OriginalCreationDateBuilder builder) {
        return builder.build();
    }
}
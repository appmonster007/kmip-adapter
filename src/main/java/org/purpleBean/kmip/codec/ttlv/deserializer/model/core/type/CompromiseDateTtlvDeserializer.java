package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.CompromiseDate;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.time.OffsetDateTime;

public class CompromiseDateTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<CompromiseDate, CompromiseDate.CompromiseDateBuilder> {

    public CompromiseDateTtlvDeserializer() {
        super(CompromiseDate.kmipTag, CompromiseDate.encodingType);
    }

    @Override
    protected CompromiseDate.CompromiseDateBuilder createBuilder() {
        return CompromiseDate.builder();
    }

    @Override
    protected void setValue(CompromiseDate.CompromiseDateBuilder builder, byte[] tagBytes, byte type, ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
        builder.value(mapper.readValue(byteBuffer, OffsetDateTime.class));
    }

    @Override
    protected CompromiseDate build(CompromiseDate.CompromiseDateBuilder builder) {
        return builder.build();
    }
}

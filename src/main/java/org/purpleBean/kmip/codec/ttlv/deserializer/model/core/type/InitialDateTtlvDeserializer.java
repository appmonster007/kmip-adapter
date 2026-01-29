package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.InitialDate;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.time.OffsetDateTime;

public class InitialDateTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<InitialDate, InitialDate.InitialDateBuilder> {

    public InitialDateTtlvDeserializer() {
        super(InitialDate.kmipTag, InitialDate.encodingType);
    }

    @Override
    protected InitialDate.InitialDateBuilder createBuilder() {
        return InitialDate.builder();
    }

    @Override
    protected void setValue(InitialDate.InitialDateBuilder builder, byte[] tagBytes, byte type, ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
        builder.value(mapper.readValue(byteBuffer, OffsetDateTime.class));
    }

    @Override
    protected InitialDate build(InitialDate.InitialDateBuilder builder) {
        return builder.build();
    }
}

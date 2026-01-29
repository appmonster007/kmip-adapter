package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.ProtectStopDate;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.time.OffsetDateTime;

public class ProtectStopDateTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<ProtectStopDate, ProtectStopDate.ProtectStopDateBuilder> {

    public ProtectStopDateTtlvDeserializer() {
        super(ProtectStopDate.kmipTag, ProtectStopDate.encodingType);
    }

    @Override
    protected ProtectStopDate.ProtectStopDateBuilder createBuilder() {
        return ProtectStopDate.builder();
    }

    @Override
    protected void setValue(ProtectStopDate.ProtectStopDateBuilder builder, byte[] tagBytes, ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
        builder.value(mapper.readValue(byteBuffer, OffsetDateTime.class));
    }

    @Override
    protected ProtectStopDate build(ProtectStopDate.ProtectStopDateBuilder builder) {
        return builder.build();
    }
}
package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.DeactivationDate;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.time.OffsetDateTime;

public class DeactivationDateTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<DeactivationDate, DeactivationDate.DeactivationDateBuilder> {

    public DeactivationDateTtlvDeserializer() {
        super(DeactivationDate.kmipTag, DeactivationDate.encodingType);
    }

    @Override
    protected DeactivationDate.DeactivationDateBuilder createBuilder() {
        return DeactivationDate.builder();
    }

    @Override
    protected void setValue(DeactivationDate.DeactivationDateBuilder builder, byte[] tag, byte type, ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
        builder.value(mapper.readValue(byteBuffer, OffsetDateTime.class));
    }

    @Override
    protected DeactivationDate build(DeactivationDate.DeactivationDateBuilder builder) {
        return builder.build();
    }
}

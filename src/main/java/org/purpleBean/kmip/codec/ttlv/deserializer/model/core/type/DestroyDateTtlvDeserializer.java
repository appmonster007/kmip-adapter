package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.DestroyDate;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.time.OffsetDateTime;

public class DestroyDateTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<DestroyDate, DestroyDate.DestroyDateBuilder> {

    public DestroyDateTtlvDeserializer() {
        super(DestroyDate.kmipTag, DestroyDate.encodingType);
    }

    @Override
    protected DestroyDate.DestroyDateBuilder createBuilder() {
        return DestroyDate.builder();
    }

    @Override
    protected void setValue(DestroyDate.DestroyDateBuilder builder, byte[] tag, byte type, ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
        builder.value(mapper.readValue(byteBuffer, OffsetDateTime.class));
    }

    @Override
    protected DestroyDate build(DestroyDate.DestroyDateBuilder builder) {
        return builder.build();
    }
}

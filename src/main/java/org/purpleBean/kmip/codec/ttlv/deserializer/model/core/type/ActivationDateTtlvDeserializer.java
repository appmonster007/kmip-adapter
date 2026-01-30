package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.ActivationDate;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.time.OffsetDateTime;

public class ActivationDateTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<ActivationDate, ActivationDate.ActivationDateBuilder> {

    public ActivationDateTtlvDeserializer() {
        super(ActivationDate.kmipTag, ActivationDate.encodingType);
    }

    @Override
    protected ActivationDate.ActivationDateBuilder createBuilder() {
        return ActivationDate.builder();
    }

    @Override
    protected void setValue(ActivationDate.ActivationDateBuilder builder, byte[] tag, byte type, ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
        builder.value(mapper.readValue(byteBuffer, OffsetDateTime.class));
    }

    @Override
    protected ActivationDate build(ActivationDate.ActivationDateBuilder builder) {
        return builder.build();
    }
}
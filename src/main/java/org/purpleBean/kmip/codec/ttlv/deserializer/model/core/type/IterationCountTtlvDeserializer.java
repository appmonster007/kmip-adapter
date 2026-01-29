package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.IterationCount;

import java.io.IOException;
import java.nio.ByteBuffer;

public class IterationCountTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<IterationCount, IterationCount.IterationCountBuilder> {

    public IterationCountTtlvDeserializer() {
        super(IterationCount.kmipTag, IterationCount.encodingType);
    }

    @Override
    protected IterationCount.IterationCountBuilder createBuilder() {
        return IterationCount.builder();
    }

    @Override
    protected void setValue(IterationCount.IterationCountBuilder builder, byte[] tagBytes, ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
        builder.value(mapper.readValue(byteBuffer, Integer.class));
    }

    @Override
    protected IterationCount build(IterationCount.IterationCountBuilder builder) {
        return builder.build();
    }
}

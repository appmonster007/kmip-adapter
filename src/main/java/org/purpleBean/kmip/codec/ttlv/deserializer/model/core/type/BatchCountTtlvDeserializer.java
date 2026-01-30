package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.BatchCount;

import java.io.IOException;
import java.nio.ByteBuffer;

public class BatchCountTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<BatchCount, BatchCount.BatchCountBuilder> {

    public BatchCountTtlvDeserializer() {
        super(BatchCount.kmipTag, BatchCount.encodingType);
    }

    @Override
    protected BatchCount.BatchCountBuilder createBuilder() {
        return BatchCount.builder();
    }

    @Override
    protected void setValue(BatchCount.BatchCountBuilder builder, byte[] tag, byte type, ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
        builder.value(mapper.readValue(byteBuffer, Integer.class));
    }

    @Override
    protected BatchCount build(BatchCount.BatchCountBuilder builder) {
        return builder.build();
    }
}

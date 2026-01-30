package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.enumeration.BatchErrorContinuationOption;

import java.io.IOException;
import java.nio.ByteBuffer;

public class BatchErrorContinuationOptionTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<BatchErrorContinuationOption, BatchErrorContinuationOption.BatchErrorContinuationOptionBuilder> {

    public BatchErrorContinuationOptionTtlvDeserializer() {
        super(BatchErrorContinuationOption.kmipTag, BatchErrorContinuationOption.encodingType);
    }

    @Override
    protected BatchErrorContinuationOption.BatchErrorContinuationOptionBuilder createBuilder() {
        return BatchErrorContinuationOption.builder();
    }

    @Override
    protected void setValue(BatchErrorContinuationOption.BatchErrorContinuationOptionBuilder builder, byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
        Integer value = mapper.readValue(p, Integer.class);
        builder.value(BatchErrorContinuationOption.fromValue(value));
    }

    @Override
    protected BatchErrorContinuationOption build(BatchErrorContinuationOption.BatchErrorContinuationOptionBuilder builder) {
        return builder.build();
    }
}

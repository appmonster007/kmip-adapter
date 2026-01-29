package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.enumeration.CancellationResult;

import java.io.IOException;
import java.nio.ByteBuffer;

public class CancellationResultTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<CancellationResult, CancellationResult.CancellationResultBuilder> {

    public CancellationResultTtlvDeserializer() {
        super(CancellationResult.kmipTag, CancellationResult.encodingType);
    }

    @Override
    protected CancellationResult.CancellationResultBuilder createBuilder() {
        return CancellationResult.builder();
    }

    @Override
    protected void setValue(CancellationResult.CancellationResultBuilder builder, byte[] tagBytes, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
        Integer value = mapper.readValue(p, Integer.class);
        builder.value(CancellationResult.fromValue(value));
    }

    @Override
    protected CancellationResult build(CancellationResult.CancellationResultBuilder builder) {
        return builder.build();
    }
}

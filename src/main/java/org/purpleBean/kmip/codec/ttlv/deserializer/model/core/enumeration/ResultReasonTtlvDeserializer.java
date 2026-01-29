package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.enumeration.ResultReason;

import java.io.IOException;
import java.nio.ByteBuffer;

public class ResultReasonTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<ResultReason, ResultReason.ResultReasonBuilder> {

    public ResultReasonTtlvDeserializer() {
        super(ResultReason.kmipTag, ResultReason.encodingType);
    }

    @Override
    protected ResultReason.ResultReasonBuilder createBuilder() {
        return ResultReason.builder();
    }

    @Override
    protected void setValue(ResultReason.ResultReasonBuilder builder, byte[] tagBytes, ByteBuffer p, TtlvMapper mapper) throws IOException {
        Integer value = mapper.readValue(p, Integer.class);
        builder.value(ResultReason.fromValue(value));
    }

    @Override
    protected ResultReason build(ResultReason.ResultReasonBuilder builder) {
        return builder.build();
    }
}

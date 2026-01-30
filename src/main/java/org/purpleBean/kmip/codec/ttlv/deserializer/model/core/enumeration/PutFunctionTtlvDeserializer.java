package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.enumeration.PutFunction;

import java.io.IOException;
import java.nio.ByteBuffer;

public class PutFunctionTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<PutFunction, PutFunction.PutFunctionBuilder> {

    public PutFunctionTtlvDeserializer() {
        super(PutFunction.kmipTag, PutFunction.encodingType);
    }

    @Override
    protected PutFunction.PutFunctionBuilder createBuilder() {
        return PutFunction.builder();
    }

    @Override
    protected void setValue(PutFunction.PutFunctionBuilder builder, byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
        Integer value = mapper.readValue(p, Integer.class);
        builder.value(PutFunction.fromValue(value));
    }

    @Override
    protected PutFunction build(PutFunction.PutFunctionBuilder builder) {
        return builder.build();
    }
}

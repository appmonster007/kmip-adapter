package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.enumeration.SplitKeyMethod;

import java.io.IOException;
import java.nio.ByteBuffer;

public class SplitKeyMethodTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<SplitKeyMethod, SplitKeyMethod.SplitKeyMethodBuilder> {

    public SplitKeyMethodTtlvDeserializer() {
        super(SplitKeyMethod.kmipTag, SplitKeyMethod.encodingType);
    }

    @Override
    protected SplitKeyMethod.SplitKeyMethodBuilder createBuilder() {
        return SplitKeyMethod.builder();
    }

    @Override
    protected void setValue(SplitKeyMethod.SplitKeyMethodBuilder builder, byte[] tagBytes, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
        Integer value = mapper.readValue(p, Integer.class);
        builder.value(SplitKeyMethod.fromValue(value));
    }

    @Override
    protected SplitKeyMethod build(SplitKeyMethod.SplitKeyMethodBuilder builder) {
        return builder.build();
    }
}

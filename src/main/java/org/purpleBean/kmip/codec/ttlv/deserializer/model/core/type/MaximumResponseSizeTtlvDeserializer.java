package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.MaximumResponseSize;

import java.io.IOException;
import java.nio.ByteBuffer;

public class MaximumResponseSizeTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<MaximumResponseSize, MaximumResponseSize.MaximumResponseSizeBuilder> {

    public MaximumResponseSizeTtlvDeserializer() {
        super(MaximumResponseSize.kmipTag, MaximumResponseSize.encodingType);
    }

    @Override
    protected MaximumResponseSize.MaximumResponseSizeBuilder createBuilder() {
        return MaximumResponseSize.builder();
    }

    @Override
    protected void setValue(MaximumResponseSize.MaximumResponseSizeBuilder builder, byte[] tag, byte type, ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
        builder.value(mapper.readValue(byteBuffer, Integer.class));
    }

    @Override
    protected MaximumResponseSize build(MaximumResponseSize.MaximumResponseSizeBuilder builder) {
        return builder.build();
    }
}

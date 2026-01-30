package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.enumeration.AsynchronousIndicator;

import java.io.IOException;
import java.nio.ByteBuffer;

public class AsynchronousIndicatorTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<AsynchronousIndicator, AsynchronousIndicator.AsynchronousIndicatorBuilder> {

    public AsynchronousIndicatorTtlvDeserializer() {
        super(AsynchronousIndicator.kmipTag, AsynchronousIndicator.encodingType);
    }

    @Override
    protected AsynchronousIndicator.AsynchronousIndicatorBuilder createBuilder() {
        return AsynchronousIndicator.builder();
    }

    @Override
    protected void setValue(AsynchronousIndicator.AsynchronousIndicatorBuilder builder, byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
        Integer value = mapper.readValue(p, Integer.class);
        builder.value(AsynchronousIndicator.fromValue(value));
    }

    @Override
    protected AsynchronousIndicator build(AsynchronousIndicator.AsynchronousIndicatorBuilder builder) {
        return builder.build();
    }
}

package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.AsynchronousIndicator;

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
    protected void setValue(AsynchronousIndicator.AsynchronousIndicatorBuilder builder, byte[] tag, byte type, ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
        builder.value(mapper.readValue(byteBuffer, Boolean.class));
    }

    @Override
    protected AsynchronousIndicator build(AsynchronousIndicator.AsynchronousIndicatorBuilder builder) {
        return builder.build();
    }
}
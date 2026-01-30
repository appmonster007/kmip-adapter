package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.AsynchronousCorrelationValue;

import java.io.IOException;
import java.nio.ByteBuffer;

public class AsynchronousCorrelationValueTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<AsynchronousCorrelationValue, AsynchronousCorrelationValue.AsynchronousCorrelationValueBuilder> {

    public AsynchronousCorrelationValueTtlvDeserializer() {
        super(AsynchronousCorrelationValue.kmipTag, AsynchronousCorrelationValue.encodingType);
    }

    @Override
    protected AsynchronousCorrelationValue.AsynchronousCorrelationValueBuilder createBuilder() {
        return AsynchronousCorrelationValue.builder();
    }

    @Override
    protected void setValue(AsynchronousCorrelationValue.AsynchronousCorrelationValueBuilder builder, byte[] tag, byte type, ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
        builder.value(mapper.readValue(byteBuffer, ByteBuffer.class));
    }

    @Override
    protected AsynchronousCorrelationValue build(AsynchronousCorrelationValue.AsynchronousCorrelationValueBuilder builder) {
        return builder.build();
    }
}
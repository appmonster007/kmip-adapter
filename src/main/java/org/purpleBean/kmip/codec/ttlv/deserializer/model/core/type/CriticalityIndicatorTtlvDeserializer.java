package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.CriticalityIndicator;

import java.io.IOException;
import java.nio.ByteBuffer;

public class CriticalityIndicatorTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<CriticalityIndicator, CriticalityIndicator.CriticalityIndicatorBuilder> {

    public CriticalityIndicatorTtlvDeserializer() {
        super(CriticalityIndicator.kmipTag, CriticalityIndicator.encodingType);
    }

    @Override
    protected CriticalityIndicator.CriticalityIndicatorBuilder createBuilder() {
        return CriticalityIndicator.builder();
    }

    @Override
    protected void setValue(CriticalityIndicator.CriticalityIndicatorBuilder builder, byte[] tagBytes, ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
        builder.value(mapper.readValue(byteBuffer, Boolean.class));
    }

    @Override
    protected CriticalityIndicator build(CriticalityIndicator.CriticalityIndicatorBuilder builder) {
        return builder.build();
    }
}

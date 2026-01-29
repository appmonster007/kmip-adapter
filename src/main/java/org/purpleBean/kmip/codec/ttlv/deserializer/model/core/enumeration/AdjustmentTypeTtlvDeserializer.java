package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.enumeration.AdjustmentType;

import java.io.IOException;
import java.nio.ByteBuffer;

public class AdjustmentTypeTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<AdjustmentType, AdjustmentType.AdjustmentTypeBuilder> {

    public AdjustmentTypeTtlvDeserializer() {
        super(AdjustmentType.kmipTag, AdjustmentType.encodingType);
    }

    @Override
    protected AdjustmentType.AdjustmentTypeBuilder createBuilder() {
        return AdjustmentType.builder();
    }

    @Override
    protected void setValue(AdjustmentType.AdjustmentTypeBuilder builder, byte[] tagBytes, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
        Integer value = mapper.readValue(p, Integer.class);
        builder.value(AdjustmentType.fromValue(value));
    }

    @Override
    protected AdjustmentType build(AdjustmentType.AdjustmentTypeBuilder builder) {
        return builder.build();
    }
}
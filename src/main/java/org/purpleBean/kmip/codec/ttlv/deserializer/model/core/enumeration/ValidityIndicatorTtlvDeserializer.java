package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.enumeration.ValidityIndicator;

import java.io.IOException;
import java.nio.ByteBuffer;

public class ValidityIndicatorTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<ValidityIndicator, ValidityIndicator.ValidityIndicatorBuilder> {

    public ValidityIndicatorTtlvDeserializer() {
        super(ValidityIndicator.kmipTag, ValidityIndicator.encodingType);
    }

    @Override
    protected ValidityIndicator.ValidityIndicatorBuilder createBuilder() {
        return ValidityIndicator.builder();
    }

    @Override
    protected void setValue(ValidityIndicator.ValidityIndicatorBuilder builder, byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
        Integer value = mapper.readValue(p, Integer.class);
        builder.value(ValidityIndicator.fromValue(value));
    }

    @Override
    protected ValidityIndicator build(ValidityIndicator.ValidityIndicatorBuilder builder) {
        return builder.build();
    }
}

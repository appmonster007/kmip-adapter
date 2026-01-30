package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.enumeration.ValidationType;

import java.io.IOException;
import java.nio.ByteBuffer;

public class ValidationTypeTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<ValidationType, ValidationType.ValidationTypeBuilder> {

    public ValidationTypeTtlvDeserializer() {
        super(ValidationType.kmipTag, ValidationType.encodingType);
    }

    @Override
    protected ValidationType.ValidationTypeBuilder createBuilder() {
        return ValidationType.builder();
    }

    @Override
    protected void setValue(ValidationType.ValidationTypeBuilder builder, byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
        Integer value = mapper.readValue(p, Integer.class);
        builder.value(ValidationType.fromValue(value));
    }

    @Override
    protected ValidationType build(ValidationType.ValidationTypeBuilder builder) {
        return builder.build();
    }
}

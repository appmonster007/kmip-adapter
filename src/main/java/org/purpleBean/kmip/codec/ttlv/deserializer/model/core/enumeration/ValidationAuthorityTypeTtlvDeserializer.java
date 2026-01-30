package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.enumeration.ValidationAuthorityType;

import java.io.IOException;
import java.nio.ByteBuffer;

public class ValidationAuthorityTypeTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<ValidationAuthorityType, ValidationAuthorityType.ValidationAuthorityTypeBuilder> {

    public ValidationAuthorityTypeTtlvDeserializer() {
        super(ValidationAuthorityType.kmipTag, ValidationAuthorityType.encodingType);
    }

    @Override
    protected ValidationAuthorityType.ValidationAuthorityTypeBuilder createBuilder() {
        return ValidationAuthorityType.builder();
    }

    @Override
    protected void setValue(ValidationAuthorityType.ValidationAuthorityTypeBuilder builder, byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
        Integer value = mapper.readValue(p, Integer.class);
        builder.value(ValidationAuthorityType.fromValue(value));
    }

    @Override
    protected ValidationAuthorityType build(ValidationAuthorityType.ValidationAuthorityTypeBuilder builder) {
        return builder.build();
    }
}

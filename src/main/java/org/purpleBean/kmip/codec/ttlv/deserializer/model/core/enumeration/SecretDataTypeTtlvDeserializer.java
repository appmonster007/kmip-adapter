package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.enumeration.SecretDataType;

import java.io.IOException;
import java.nio.ByteBuffer;

public class SecretDataTypeTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<SecretDataType, SecretDataType.SecretDataTypeBuilder> {

    public SecretDataTypeTtlvDeserializer() {
        super(SecretDataType.kmipTag, SecretDataType.encodingType);
    }

    @Override
    protected SecretDataType.SecretDataTypeBuilder createBuilder() {
        return SecretDataType.builder();
    }

    @Override
    protected void setValue(SecretDataType.SecretDataTypeBuilder builder, byte[] tagBytes, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
        Integer value = mapper.readValue(p, Integer.class);
        builder.value(SecretDataType.fromValue(value));
    }

    @Override
    protected SecretDataType build(SecretDataType.SecretDataTypeBuilder builder) {
        return builder.build();
    }
}

package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.enumeration.RotateNameType;

import java.io.IOException;
import java.nio.ByteBuffer;

public class RotateNameTypeTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<RotateNameType, RotateNameType.RotateNameTypeBuilder> {

    public RotateNameTypeTtlvDeserializer() {
        super(RotateNameType.kmipTag, RotateNameType.encodingType);
    }

    @Override
    protected RotateNameType.RotateNameTypeBuilder createBuilder() {
        return RotateNameType.builder();
    }

    @Override
    protected void setValue(RotateNameType.RotateNameTypeBuilder builder, byte[] tagBytes, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
        Integer value = mapper.readValue(p, Integer.class);
        builder.value(RotateNameType.fromValue(value));
    }

    @Override
    protected RotateNameType build(RotateNameType.RotateNameTypeBuilder builder) {
        return builder.build();
    }
}

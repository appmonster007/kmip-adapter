package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.enumeration.NameType;

import java.io.IOException;
import java.nio.ByteBuffer;

public class NameTypeTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<NameType, NameType.NameTypeBuilder> {

    public NameTypeTtlvDeserializer() {
        super(NameType.kmipTag, NameType.encodingType);
    }

    @Override
    protected NameType.NameTypeBuilder createBuilder() {
        return NameType.builder();
    }

    @Override
    protected void setValue(NameType.NameTypeBuilder builder, byte[] tagBytes, ByteBuffer p, TtlvMapper mapper) throws IOException {
        Integer value = mapper.readValue(p, Integer.class);
        builder.value(NameType.fromValue(value));
    }

    @Override
    protected NameType build(NameType.NameTypeBuilder builder) {
        return builder.build();
    }
}

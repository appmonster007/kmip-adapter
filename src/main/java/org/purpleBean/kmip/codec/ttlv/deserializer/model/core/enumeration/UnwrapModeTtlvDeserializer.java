package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.enumeration.UnwrapMode;

import java.io.IOException;
import java.nio.ByteBuffer;

public class UnwrapModeTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<UnwrapMode, UnwrapMode.UnwrapModeBuilder> {

    public UnwrapModeTtlvDeserializer() {
        super(UnwrapMode.kmipTag, UnwrapMode.encodingType);
    }

    @Override
    protected UnwrapMode.UnwrapModeBuilder createBuilder() {
        return UnwrapMode.builder();
    }

    @Override
    protected void setValue(UnwrapMode.UnwrapModeBuilder builder, byte[] tagBytes, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
        Integer value = mapper.readValue(p, Integer.class);
        builder.value(UnwrapMode.fromValue(value));
    }

    @Override
    protected UnwrapMode build(UnwrapMode.UnwrapModeBuilder builder) {
        return builder.build();
    }
}

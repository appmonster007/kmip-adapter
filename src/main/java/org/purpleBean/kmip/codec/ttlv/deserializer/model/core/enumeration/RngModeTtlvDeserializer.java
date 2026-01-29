package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.enumeration.RngMode;

import java.io.IOException;
import java.nio.ByteBuffer;

public class RngModeTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<RngMode, RngMode.RngModeBuilder> {

    public RngModeTtlvDeserializer() {
        super(RngMode.kmipTag, RngMode.encodingType);
    }

    @Override
    protected RngMode.RngModeBuilder createBuilder() {
        return RngMode.builder();
    }

    @Override
    protected void setValue(RngMode.RngModeBuilder builder, byte[] tagBytes, ByteBuffer p, TtlvMapper mapper) throws IOException {
        Integer value = mapper.readValue(p, Integer.class);
        builder.value(RngMode.fromValue(value));
    }

    @Override
    protected RngMode build(RngMode.RngModeBuilder builder) {
        return builder.build();
    }
}

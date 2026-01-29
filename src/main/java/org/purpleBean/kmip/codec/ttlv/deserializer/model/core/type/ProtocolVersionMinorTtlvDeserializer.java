package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.ProtocolVersionMinor;

import java.io.IOException;
import java.nio.ByteBuffer;

public class ProtocolVersionMinorTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<ProtocolVersionMinor, ProtocolVersionMinor.ProtocolVersionMinorBuilder> {

    public ProtocolVersionMinorTtlvDeserializer() {
        super(ProtocolVersionMinor.kmipTag, ProtocolVersionMinor.encodingType);
    }

    @Override
    protected ProtocolVersionMinor.ProtocolVersionMinorBuilder createBuilder() {
        return ProtocolVersionMinor.builder();
    }

    @Override
    protected void setValue(ProtocolVersionMinor.ProtocolVersionMinorBuilder builder, byte[] tagBytes, ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
        builder.value(mapper.readValue(byteBuffer, Integer.class));
    }

    @Override
    protected ProtocolVersionMinor build(ProtocolVersionMinor.ProtocolVersionMinorBuilder builder) {
        return builder.build();
    }
}

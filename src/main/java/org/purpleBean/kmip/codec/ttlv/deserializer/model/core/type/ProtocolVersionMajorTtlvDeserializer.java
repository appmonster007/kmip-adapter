package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.ProtocolVersionMajor;

import java.io.IOException;
import java.nio.ByteBuffer;

public class ProtocolVersionMajorTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<ProtocolVersionMajor, ProtocolVersionMajor.ProtocolVersionMajorBuilder> {

    public ProtocolVersionMajorTtlvDeserializer() {
        super(ProtocolVersionMajor.kmipTag, ProtocolVersionMajor.encodingType);
    }

    @Override
    protected ProtocolVersionMajor.ProtocolVersionMajorBuilder createBuilder() {
        return ProtocolVersionMajor.builder();
    }

    @Override
    protected void setValue(ProtocolVersionMajor.ProtocolVersionMajorBuilder builder, byte[] tagBytes, ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
        builder.value(mapper.readValue(byteBuffer, Integer.class));
    }

    @Override
    protected ProtocolVersionMajor build(ProtocolVersionMajor.ProtocolVersionMajorBuilder builder) {
        return builder.build();
    }
}

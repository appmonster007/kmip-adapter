package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.InitializationVector;

import java.io.IOException;
import java.nio.ByteBuffer;

public class InitializationVectorTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<InitializationVector, InitializationVector.InitializationVectorBuilder> {

    public InitializationVectorTtlvDeserializer() {
        super(InitializationVector.kmipTag, InitializationVector.encodingType);
    }

    @Override
    protected InitializationVector.InitializationVectorBuilder createBuilder() {
        return InitializationVector.builder();
    }

    @Override
    protected void setValue(InitializationVector.InitializationVectorBuilder builder, byte[] tagBytes, byte type, ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
        builder.value(mapper.readValue(byteBuffer, ByteBuffer.class));
    }

    @Override
    protected InitializationVector build(InitializationVector.InitializationVectorBuilder builder) {
        return builder.build();
    }
}

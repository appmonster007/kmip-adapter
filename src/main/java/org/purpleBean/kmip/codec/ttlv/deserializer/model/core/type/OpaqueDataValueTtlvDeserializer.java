package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.OpaqueDataValue;

import java.io.IOException;
import java.nio.ByteBuffer;

public class OpaqueDataValueTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<OpaqueDataValue, OpaqueDataValue.OpaqueDataValueBuilder> {

    public OpaqueDataValueTtlvDeserializer() {
        super(OpaqueDataValue.kmipTag, OpaqueDataValue.encodingType);
    }

    @Override
    protected OpaqueDataValue.OpaqueDataValueBuilder createBuilder() {
        return OpaqueDataValue.builder();
    }

    @Override
    protected void setValue(OpaqueDataValue.OpaqueDataValueBuilder builder, byte[] tagBytes, ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
        builder.value(mapper.readValue(byteBuffer, ByteBuffer.class));
    }

    @Override
    protected OpaqueDataValue build(OpaqueDataValue.OpaqueDataValueBuilder builder) {
        return builder.build();
    }
}
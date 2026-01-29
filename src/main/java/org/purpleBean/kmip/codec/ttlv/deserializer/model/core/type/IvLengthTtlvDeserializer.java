package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.IvLength;

import java.io.IOException;
import java.nio.ByteBuffer;

public class IvLengthTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<IvLength, IvLength.IvLengthBuilder> {

    public IvLengthTtlvDeserializer() {
        super(IvLength.kmipTag, IvLength.encodingType);
    }

    @Override
    protected IvLength.IvLengthBuilder createBuilder() {
        return IvLength.builder();
    }

    @Override
    protected void setValue(IvLength.IvLengthBuilder builder, byte[] tagBytes, ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
        builder.value(mapper.readValue(byteBuffer, Integer.class));
    }

    @Override
    protected IvLength build(IvLength.IvLengthBuilder builder) {
        return builder.build();
    }
}

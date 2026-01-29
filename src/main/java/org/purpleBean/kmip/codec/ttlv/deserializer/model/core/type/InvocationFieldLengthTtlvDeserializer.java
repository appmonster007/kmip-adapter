package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.InvocationFieldLength;

import java.io.IOException;
import java.nio.ByteBuffer;

public class InvocationFieldLengthTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<InvocationFieldLength, InvocationFieldLength.InvocationFieldLengthBuilder> {

    public InvocationFieldLengthTtlvDeserializer() {
        super(InvocationFieldLength.kmipTag, InvocationFieldLength.encodingType);
    }

    @Override
    protected InvocationFieldLength.InvocationFieldLengthBuilder createBuilder() {
        return InvocationFieldLength.builder();
    }

    @Override
    protected void setValue(InvocationFieldLength.InvocationFieldLengthBuilder builder, byte[] tagBytes, byte type, ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
        builder.value(mapper.readValue(byteBuffer, Integer.class));
    }

    @Override
    protected InvocationFieldLength build(InvocationFieldLength.InvocationFieldLengthBuilder builder) {
        return builder.build();
    }
}

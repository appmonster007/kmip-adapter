package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.J;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.ByteBuffer;

public class JTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<J, J.JBuilder> {

    public JTtlvDeserializer() {
        super(J.kmipTag, J.encodingType);
    }

    @Override
    protected J.JBuilder createBuilder() {
        return J.builder();
    }

    @Override
    protected void setValue(J.JBuilder builder, byte[] tagBytes, byte type, ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
        builder.value(mapper.readValue(byteBuffer, BigInteger.class));
    }

    @Override
    protected J build(J.JBuilder builder) {
        return builder.build();
    }
}

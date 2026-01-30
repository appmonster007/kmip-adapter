package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.X;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.ByteBuffer;

public class XTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<X, X.XBuilder> {

    public XTtlvDeserializer() {
        super(X.kmipTag, X.encodingType);
    }

    @Override
    protected X.XBuilder createBuilder() {
        return X.builder();
    }

    @Override
    protected void setValue(X.XBuilder builder, byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
        builder.value(mapper.readValue(p, BigInteger.class));
    }

    @Override
    protected X build(X.XBuilder builder) {
        return builder.build();
    }
}
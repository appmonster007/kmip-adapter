package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.D;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.ByteBuffer;

public class DTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<D, D.DBuilder> {

    public DTtlvDeserializer() {
        super(D.kmipTag, D.encodingType);
    }

    @Override
    protected D.DBuilder createBuilder() {
        return D.builder();
    }

    @Override
    protected void setValue(D.DBuilder builder, byte[] tagBytes, byte type, ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
        builder.value(mapper.readValue(byteBuffer, BigInteger.class));
    }

    @Override
    protected D build(D.DBuilder builder) {
        return builder.build();
    }
}

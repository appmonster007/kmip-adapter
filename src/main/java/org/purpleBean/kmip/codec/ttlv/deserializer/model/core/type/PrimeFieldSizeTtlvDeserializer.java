package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.PrimeFieldSize;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.ByteBuffer;

public class PrimeFieldSizeTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<PrimeFieldSize, PrimeFieldSize.PrimeFieldSizeBuilder> {

    public PrimeFieldSizeTtlvDeserializer() {
        super(PrimeFieldSize.kmipTag, PrimeFieldSize.encodingType);
    }

    @Override
    protected PrimeFieldSize.PrimeFieldSizeBuilder createBuilder() {
        return PrimeFieldSize.builder();
    }

    @Override
    protected void setValue(PrimeFieldSize.PrimeFieldSizeBuilder builder, byte[] tagBytes, byte type, ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
        builder.value(mapper.readValue(byteBuffer, BigInteger.class));
    }

    @Override
    protected PrimeFieldSize build(PrimeFieldSize.PrimeFieldSizeBuilder builder) {
        return builder.build();
    }
}
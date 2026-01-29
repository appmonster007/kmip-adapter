package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.PrimeExponentP;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.ByteBuffer;

public class PrimeExponentPTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<PrimeExponentP, PrimeExponentP.PrimeExponentPBuilder> {

    public PrimeExponentPTtlvDeserializer() {
        super(PrimeExponentP.kmipTag, PrimeExponentP.encodingType);
    }

    @Override
    protected PrimeExponentP.PrimeExponentPBuilder createBuilder() {
        return PrimeExponentP.builder();
    }

    @Override
    protected void setValue(PrimeExponentP.PrimeExponentPBuilder builder, byte[] tagBytes, ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
        builder.value(mapper.readValue(byteBuffer, BigInteger.class));
    }

    @Override
    protected PrimeExponentP build(PrimeExponentP.PrimeExponentPBuilder builder) {
        return builder.build();
    }
}
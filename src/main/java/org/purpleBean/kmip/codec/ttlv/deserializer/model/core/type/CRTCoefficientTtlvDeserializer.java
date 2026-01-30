package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.CRTCoefficient;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.ByteBuffer;

public class CRTCoefficientTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<CRTCoefficient, CRTCoefficient.CRTCoefficientBuilder> {

    public CRTCoefficientTtlvDeserializer() {
        super(CRTCoefficient.kmipTag, CRTCoefficient.encodingType);
    }

    @Override
    protected CRTCoefficient.CRTCoefficientBuilder createBuilder() {
        return CRTCoefficient.builder();
    }

    @Override
    protected void setValue(CRTCoefficient.CRTCoefficientBuilder builder, byte[] tag, byte type, ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
        builder.value(mapper.readValue(byteBuffer, BigInteger.class));
    }

    @Override
    protected CRTCoefficient build(CRTCoefficient.CRTCoefficientBuilder builder) {
        return builder.build();
    }
}

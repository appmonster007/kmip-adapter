package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.enumeration.SplitKeyPolynomial;

import java.io.IOException;
import java.nio.ByteBuffer;

public class SplitKeyPolynomialTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<SplitKeyPolynomial, SplitKeyPolynomial.SplitKeyPolynomialBuilder> {

    public SplitKeyPolynomialTtlvDeserializer() {
        super(SplitKeyPolynomial.kmipTag, SplitKeyPolynomial.encodingType);
    }

    @Override
    protected SplitKeyPolynomial.SplitKeyPolynomialBuilder createBuilder() {
        return SplitKeyPolynomial.builder();
    }

    @Override
    protected void setValue(SplitKeyPolynomial.SplitKeyPolynomialBuilder builder, byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
        Integer value = mapper.readValue(p, Integer.class);
        builder.value(SplitKeyPolynomial.fromValue(value));
    }

    @Override
    protected SplitKeyPolynomial build(SplitKeyPolynomial.SplitKeyPolynomialBuilder builder) {
        return builder.build();
    }
}

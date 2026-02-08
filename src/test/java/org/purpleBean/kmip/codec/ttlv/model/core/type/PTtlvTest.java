package org.purpleBean.kmip.codec.ttlv.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.P;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

import java.math.BigInteger;

@DisplayName("P TTLV Serialization Tests")
class PTtlvTest extends AbstractTtlvSerializationTestSuite<P> {

    @Override
    public Class<P> type() {
        return P.class;
    }

    @Override
    public P createDefault() {
        return P.builder().value(BigInteger.ONE).build();
    }

    @Override
    public P createVariant() {
        return P.builder().value(BigInteger.TEN).build();
    }
}
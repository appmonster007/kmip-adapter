package org.purpleBean.kmip.codec.ttlv.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.P;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

import java.math.BigInteger;

@DisplayName("P TTLV Serialization Tests")
class PTtlvTest extends AbstractTtlvSerializationTestSuite<P> {

    @Override
    protected Class<P> type() {
        return P.class;
    }

    @Override
    protected P createDefault() {
        return P.builder().value(BigInteger.ONE).build();
    }

    @Override
    protected P createVariant() {
        return P.builder().value(BigInteger.TEN).build();
    }
}
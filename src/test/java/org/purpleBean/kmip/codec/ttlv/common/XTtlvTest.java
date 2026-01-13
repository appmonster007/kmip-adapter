package org.purpleBean.kmip.codec.ttlv.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.X;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

import java.math.BigInteger;

@DisplayName("X TTLV Serialization Tests")
class XTtlvTest extends AbstractTtlvSerializationTestSuite<X> {

    @Override
    protected Class<X> type() {
        return X.class;
    }

    @Override
    protected X createDefault() {
        return X.builder().value(BigInteger.ONE).build();
    }

    @Override
    protected X createVariant() {
        return X.builder().value(BigInteger.TEN).build();
    }
}
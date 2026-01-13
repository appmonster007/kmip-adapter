package org.purpleBean.kmip.codec.ttlv.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.D;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

import java.math.BigInteger;

@DisplayName("D TTLV Serialization Tests")
class DTtlvTest extends AbstractTtlvSerializationTestSuite<D> {

    @Override
    protected Class<D> type() {
        return D.class;
    }

    @Override
    protected D createDefault() {
        return D.builder().value(BigInteger.ONE).build();
    }

    @Override
    protected D createVariant() {
        return D.builder().value(BigInteger.TEN).build();
    }
}
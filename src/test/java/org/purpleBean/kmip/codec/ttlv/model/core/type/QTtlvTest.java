package org.purpleBean.kmip.codec.ttlv.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.Q;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

import java.math.BigInteger;

@DisplayName("Q TTLV Serialization Tests")
class QTtlvTest extends AbstractTtlvSerializationTestSuite<Q> {

    @Override
    protected Class<Q> type() {
        return Q.class;
    }

    @Override
    protected Q createDefault() {
        return Q.builder().value(BigInteger.ONE).build();
    }

    @Override
    protected Q createVariant() {
        return Q.builder().value(BigInteger.TEN).build();
    }
}
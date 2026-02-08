package org.purpleBean.kmip.codec.ttlv.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.Q;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

import java.math.BigInteger;

@DisplayName("Q TTLV Serialization Tests")
class QTtlvTest extends AbstractTtlvSerializationTestSuite<Q> {

    @Override
    public Class<Q> type() {
        return Q.class;
    }

    @Override
    public Q createDefault() {
        return Q.builder().value(BigInteger.ONE).build();
    }

    @Override
    public Q createVariant() {
        return Q.builder().value(BigInteger.TEN).build();
    }
}
package org.purpleBean.kmip.codec.json.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.Q;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

import java.math.BigInteger;

@DisplayName("Q JSON Serialization Tests")
class QJsonTest extends AbstractJsonSerializationTestSuite<Q> {

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
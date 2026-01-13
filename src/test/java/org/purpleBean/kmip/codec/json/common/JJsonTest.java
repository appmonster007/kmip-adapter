package org.purpleBean.kmip.codec.json.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.J;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

import java.math.BigInteger;

@DisplayName("J JSON Serialization Tests")
class JJsonTest extends AbstractJsonSerializationTestSuite<J> {

    @Override
    protected Class<J> type() {
        return J.class;
    }

    @Override
    protected J createDefault() {
        return J.builder().value(BigInteger.ONE).build();
    }

    @Override
    protected J createVariant() {
        return J.builder().value(BigInteger.TEN).build();
    }
}
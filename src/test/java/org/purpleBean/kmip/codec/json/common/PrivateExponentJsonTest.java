package org.purpleBean.kmip.codec.json.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.PrivateExponent;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

import java.math.BigInteger;

@DisplayName("PrivateExponent JSON Serialization Tests")
class PrivateExponentJsonTest extends AbstractJsonSerializationTestSuite<PrivateExponent> {

    @Override
    protected Class<PrivateExponent> type() {
        return PrivateExponent.class;
    }

    @Override
    protected PrivateExponent createDefault() {
        return PrivateExponent.builder().value(BigInteger.valueOf(12345)).build();
    }

    @Override
    protected PrivateExponent createVariant() {
        return PrivateExponent.builder().value(BigInteger.valueOf(54321)).build();
    }
}
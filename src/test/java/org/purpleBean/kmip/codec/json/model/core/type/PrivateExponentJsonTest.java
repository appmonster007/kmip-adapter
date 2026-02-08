package org.purpleBean.kmip.codec.json.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.PrivateExponent;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

import java.math.BigInteger;

@DisplayName("PrivateExponent JSON Serialization Tests")
class PrivateExponentJsonTest extends AbstractJsonSerializationTestSuite<PrivateExponent> {

    @Override
    public Class<PrivateExponent> type() {
        return PrivateExponent.class;
    }

    @Override
    public PrivateExponent createDefault() {
        return PrivateExponent.builder().value(BigInteger.valueOf(12345)).build();
    }

    @Override
    public PrivateExponent createVariant() {
        return PrivateExponent.builder().value(BigInteger.valueOf(54321)).build();
    }
}
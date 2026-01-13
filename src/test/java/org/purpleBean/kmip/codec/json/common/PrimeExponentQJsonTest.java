package org.purpleBean.kmip.codec.json.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.PrimeExponentQ;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

import java.math.BigInteger;

@DisplayName("PrimeExponentQ JSON Serialization Tests")
class PrimeExponentQJsonTest extends AbstractJsonSerializationTestSuite<PrimeExponentQ> {

    @Override
    protected Class<PrimeExponentQ> type() {
        return PrimeExponentQ.class;
    }

    @Override
    protected PrimeExponentQ createDefault() {
        return PrimeExponentQ.builder().value(BigInteger.valueOf(65537)).build();
    }

    @Override
    protected PrimeExponentQ createVariant() {
        return PrimeExponentQ.builder().value(BigInteger.valueOf(3)).build();
    }
}
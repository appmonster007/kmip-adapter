package org.purpleBean.kmip.codec.json.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.P;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

import java.math.BigInteger;

@DisplayName("P JSON Serialization Tests")
class PJsonTest extends AbstractJsonSerializationTestSuite<P> {

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
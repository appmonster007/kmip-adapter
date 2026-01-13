package org.purpleBean.kmip.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.test.suite.AbstractKmipDataTypeTestSuite;

import java.math.BigInteger;

@DisplayName("Q Domain Tests")
class QTest extends AbstractKmipDataTypeTestSuite<Q> {

    @Override
    protected Class<Q> type() {
        return Q.class;
    }

    @Override
    protected Q createDefault() {
        return Q.builder().value(BigInteger.ONE).build();
    }

    @Override
    protected EncodingType expectedEncodingType() {
        return EncodingType.BIG_INTEGER;
    }
}
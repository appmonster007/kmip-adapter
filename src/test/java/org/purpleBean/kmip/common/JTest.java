package org.purpleBean.kmip.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.test.suite.AbstractKmipDataTypeTestSuite;

import java.math.BigInteger;

@DisplayName("J Domain Tests")
class JTest extends AbstractKmipDataTypeTestSuite<J> {

    @Override
    protected Class<J> type() {
        return J.class;
    }

    @Override
    protected J createDefault() {
        return J.builder().value(BigInteger.ONE).build();
    }

    @Override
    protected EncodingType expectedEncodingType() {
        return EncodingType.BIG_INTEGER;
    }
}
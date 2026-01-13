package org.purpleBean.kmip.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.test.suite.AbstractKmipDataTypeTestSuite;

import java.math.BigInteger;

@DisplayName("PrivateExponent Domain Tests")
class PrivateExponentTest extends AbstractKmipDataTypeTestSuite<PrivateExponent> {

    @Override
    protected Class<PrivateExponent> type() {
        return PrivateExponent.class;
    }

    @Override
    protected PrivateExponent createDefault() {
        return PrivateExponent.builder().value(BigInteger.valueOf(12345)).build();
    }

    @Override
    protected EncodingType expectedEncodingType() {
        return EncodingType.BIG_INTEGER;
    }
}
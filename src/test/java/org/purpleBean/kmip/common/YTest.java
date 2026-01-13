package org.purpleBean.kmip.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.test.suite.AbstractKmipDataTypeTestSuite;

import java.math.BigInteger;

@DisplayName("Y Domain Tests")
class YTest extends AbstractKmipDataTypeTestSuite<Y> {

    @Override
    protected Class<Y> type() {
        return Y.class;
    }

    @Override
    protected Y createDefault() {
        return Y.builder().value(BigInteger.ONE).build();
    }

    @Override
    protected EncodingType expectedEncodingType() {
        return EncodingType.BIG_INTEGER;
    }
}
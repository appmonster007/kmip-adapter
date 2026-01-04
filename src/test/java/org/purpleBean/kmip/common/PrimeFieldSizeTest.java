package org.purpleBean.kmip.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.test.suite.AbstractKmipDataTypeSuite;

import java.math.BigInteger;

@DisplayName("PrimeFieldSize Domain Tests")
class PrimeFieldSizeTest extends AbstractKmipDataTypeSuite<PrimeFieldSize> {

    @Override
    protected Class<PrimeFieldSize> type() {
        return PrimeFieldSize.class;
    }

    @Override
    protected PrimeFieldSize createDefault() {
        return PrimeFieldSize.builder().value(BigInteger.valueOf(2048)).build();
    }

    @Override
    protected EncodingType expectedEncodingType() {
        return EncodingType.BIG_INTEGER;
    }
}
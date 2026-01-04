package org.purpleBean.kmip.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.test.suite.AbstractKmipDataTypeSuite;

import java.math.BigInteger;

@DisplayName("PrimeExponentQ Domain Tests")
class PrimeExponentQTest extends AbstractKmipDataTypeSuite<PrimeExponentQ> {

    @Override
    protected Class<PrimeExponentQ> type() {
        return PrimeExponentQ.class;
    }

    @Override
    protected PrimeExponentQ createDefault() {
        return PrimeExponentQ.builder().value(BigInteger.valueOf(65537)).build();
    }

    @Override
    protected EncodingType expectedEncodingType() {
        return EncodingType.BIG_INTEGER;
    }
}
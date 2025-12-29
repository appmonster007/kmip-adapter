package org.purpleBean.kmip.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipDataType;
import org.purpleBean.kmip.common.enumeration.State;
import org.purpleBean.kmip.test.suite.AbstractKmipDataTypeSuite;

import java.math.BigInteger;
import java.util.List;

@DisplayName("PrimeExponentP Domain Tests")
class PrimeExponentPTest extends AbstractKmipDataTypeSuite<PrimeExponentP> {

    @Override
    protected Class<PrimeExponentP> type() {
        return PrimeExponentP.class;
    }

    @Override
    protected PrimeExponentP createDefault() {
        return PrimeExponentP.builder().value(BigInteger.valueOf(65537)).build();
    }

    @Override
    protected EncodingType expectedEncodingType() {
        return EncodingType.BIG_INTEGER;
    }
}
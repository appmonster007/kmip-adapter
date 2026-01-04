package org.purpleBean.kmip.codec.ttlv.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.PrimeExponentP;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationSuite;

import java.math.BigInteger;

@DisplayName("PrimeExponentP TTLV Serialization Tests")
class PrimeExponentPTtlvTest extends AbstractTtlvSerializationSuite<PrimeExponentP> {

    @Override
    protected Class<PrimeExponentP> type() {
        return PrimeExponentP.class;
    }

    @Override
    protected PrimeExponentP createDefault() {
        return PrimeExponentP.builder().value(BigInteger.valueOf(65537)).build();
    }

    @Override
    protected PrimeExponentP createVariant() {
        return PrimeExponentP.builder().value(BigInteger.valueOf(3)).build();
    }
}
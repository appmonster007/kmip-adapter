package org.purpleBean.kmip.codec.ttlv.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.PrimeExponentP;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

import java.math.BigInteger;

@DisplayName("PrimeExponentP TTLV Serialization Tests")
class PrimeExponentPTtlvTest extends AbstractTtlvSerializationTestSuite<PrimeExponentP> {

    @Override
    public Class<PrimeExponentP> type() {
        return PrimeExponentP.class;
    }

    @Override
    public PrimeExponentP createDefault() {
        return PrimeExponentP.builder().value(BigInteger.valueOf(65537)).build();
    }

    @Override
    public PrimeExponentP createVariant() {
        return PrimeExponentP.builder().value(BigInteger.valueOf(3)).build();
    }
}
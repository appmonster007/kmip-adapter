package org.purpleBean.kmip.codec.ttlv.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.PrimeExponentQ;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

import java.math.BigInteger;

@DisplayName("PrimeExponentQ TTLV Serialization Tests")
class PrimeExponentQTtlvTest extends AbstractTtlvSerializationTestSuite<PrimeExponentQ> {

    @Override
    protected Class<PrimeExponentQ> type() {
        return PrimeExponentQ.class;
    }

    @Override
    protected PrimeExponentQ createDefault() {
        return PrimeExponentQ.builder().value(BigInteger.valueOf(65537)).build();
    }

    @Override
    protected PrimeExponentQ createVariant() {
        return PrimeExponentQ.builder().value(BigInteger.valueOf(3)).build();
    }
}
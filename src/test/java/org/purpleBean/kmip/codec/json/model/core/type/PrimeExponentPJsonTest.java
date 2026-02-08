package org.purpleBean.kmip.codec.json.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.PrimeExponentP;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

import java.math.BigInteger;

@DisplayName("PrimeExponentP JSON Serialization Tests")
class PrimeExponentPJsonTest extends AbstractJsonSerializationTestSuite<PrimeExponentP> {

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
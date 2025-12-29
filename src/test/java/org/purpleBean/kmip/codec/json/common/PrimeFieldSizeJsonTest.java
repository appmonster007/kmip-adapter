package org.purpleBean.kmip.codec.json.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.PrimeFieldSize;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationSuite;

import java.math.BigInteger;

@DisplayName("PrimeFieldSize JSON Serialization Tests")
class PrimeFieldSizeJsonTest extends AbstractJsonSerializationSuite<PrimeFieldSize> {

    @Override
    protected Class<PrimeFieldSize> type() {
        return PrimeFieldSize.class;
    }

    @Override
    protected PrimeFieldSize createDefault() {
        return PrimeFieldSize.builder().value(BigInteger.valueOf(2048)).build();
    }

    @Override
    protected PrimeFieldSize createVariant() {
        return PrimeFieldSize.builder().value(BigInteger.valueOf(3072)).build();
    }
}
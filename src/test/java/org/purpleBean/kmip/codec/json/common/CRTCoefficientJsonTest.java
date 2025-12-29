package org.purpleBean.kmip.codec.json.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.CRTCoefficient;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationSuite;

import java.math.BigInteger;

@DisplayName("CRTCoefficient JSON Serialization Tests")
class CRTCoefficientJsonTest extends AbstractJsonSerializationSuite<CRTCoefficient> {

    @Override
    protected Class<CRTCoefficient> type() {
        return CRTCoefficient.class;
    }

    @Override
    protected CRTCoefficient createDefault() {
        return CRTCoefficient.builder().value(BigInteger.valueOf(12345)).build();
    }

    @Override
    protected CRTCoefficient createVariant() {
        return CRTCoefficient.builder().value(BigInteger.valueOf(54321)).build();
    }
}
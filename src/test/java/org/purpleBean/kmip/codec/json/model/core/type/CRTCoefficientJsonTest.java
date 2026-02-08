package org.purpleBean.kmip.codec.json.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.CRTCoefficient;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

import java.math.BigInteger;

@DisplayName("CRTCoefficient JSON Serialization Tests")
class CRTCoefficientJsonTest extends AbstractJsonSerializationTestSuite<CRTCoefficient> {

    @Override
    public Class<CRTCoefficient> type() {
        return CRTCoefficient.class;
    }

    @Override
    public CRTCoefficient createDefault() {
        return CRTCoefficient.builder().value(BigInteger.valueOf(12345)).build();
    }

    @Override
    public CRTCoefficient createVariant() {
        return CRTCoefficient.builder().value(BigInteger.valueOf(54321)).build();
    }
}
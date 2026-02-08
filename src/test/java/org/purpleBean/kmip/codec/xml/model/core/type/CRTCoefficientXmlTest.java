package org.purpleBean.kmip.codec.xml.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.CRTCoefficient;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

import java.math.BigInteger;

@DisplayName("CRTCoefficient XML Serialization Tests")
class CRTCoefficientXmlTest extends AbstractXmlSerializationTestSuite<CRTCoefficient> {

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
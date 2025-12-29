package org.purpleBean.kmip.codec.xml.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.CRTCoefficient;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationSuite;

import java.math.BigInteger;

@DisplayName("CRTCoefficient XML Serialization Tests")
class CRTCoefficientXmlTest extends AbstractXmlSerializationSuite<CRTCoefficient> {

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
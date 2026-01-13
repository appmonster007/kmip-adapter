package org.purpleBean.kmip.codec.xml.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.PrivateExponent;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

import java.math.BigInteger;

@DisplayName("PrivateExponent XML Serialization Tests")
class PrivateExponentXmlTest extends AbstractXmlSerializationTestSuite<PrivateExponent> {

    @Override
    protected Class<PrivateExponent> type() {
        return PrivateExponent.class;
    }

    @Override
    protected PrivateExponent createDefault() {
        return PrivateExponent.builder().value(BigInteger.valueOf(12345)).build();
    }

    @Override
    protected PrivateExponent createVariant() {
        return PrivateExponent.builder().value(BigInteger.valueOf(54321)).build();
    }
}
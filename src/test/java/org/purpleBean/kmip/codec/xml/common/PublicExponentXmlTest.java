package org.purpleBean.kmip.codec.xml.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.PublicExponent;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

import java.math.BigInteger;

@DisplayName("PublicExponent XML Serialization Tests")
class PublicExponentXmlTest extends AbstractXmlSerializationTestSuite<PublicExponent> {

    @Override
    protected Class<PublicExponent> type() {
        return PublicExponent.class;
    }

    @Override
    protected PublicExponent createDefault() {
        return PublicExponent.builder().value(BigInteger.valueOf(65537)).build();
    }

    @Override
    protected PublicExponent createVariant() {
        return PublicExponent.builder().value(BigInteger.valueOf(3)).build();
    }
}
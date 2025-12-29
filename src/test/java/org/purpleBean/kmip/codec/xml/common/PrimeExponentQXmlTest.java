package org.purpleBean.kmip.codec.xml.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.PrimeExponentQ;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationSuite;

import java.math.BigInteger;

@DisplayName("PrimeExponentQ XML Serialization Tests")
class PrimeExponentQXmlTest extends AbstractXmlSerializationSuite<PrimeExponentQ> {

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
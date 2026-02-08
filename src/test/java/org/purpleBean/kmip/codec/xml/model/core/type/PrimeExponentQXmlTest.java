package org.purpleBean.kmip.codec.xml.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.PrimeExponentQ;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

import java.math.BigInteger;

@DisplayName("PrimeExponentQ XML Serialization Tests")
class PrimeExponentQXmlTest extends AbstractXmlSerializationTestSuite<PrimeExponentQ> {

    @Override
    public Class<PrimeExponentQ> type() {
        return PrimeExponentQ.class;
    }

    @Override
    public PrimeExponentQ createDefault() {
        return PrimeExponentQ.builder().value(BigInteger.valueOf(65537)).build();
    }

    @Override
    public PrimeExponentQ createVariant() {
        return PrimeExponentQ.builder().value(BigInteger.valueOf(3)).build();
    }
}
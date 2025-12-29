package org.purpleBean.kmip.codec.xml.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.Modulus;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationSuite;

import java.math.BigInteger;

@DisplayName("Modulus XML Serialization Tests")
class ModulusXmlTest extends AbstractXmlSerializationSuite<Modulus> {

    @Override
    protected Class<Modulus> type() {
        return Modulus.class;
    }

    @Override
    protected Modulus createDefault() {
        return Modulus.builder().value(BigInteger.TEN).build();
    }

    @Override
    protected Modulus createVariant() {
        return Modulus.builder().value(BigInteger.valueOf(20)).build();
    }
}
package org.purpleBean.kmip.codec.xml.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.P;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

import java.math.BigInteger;

@DisplayName("P XML Serialization Tests")
class PXmlTest extends AbstractXmlSerializationTestSuite<P> {

    @Override
    public Class<P> type() {
        return P.class;
    }

    @Override
    public P createDefault() {
        return P.builder().value(BigInteger.ONE).build();
    }

    @Override
    public P createVariant() {
        return P.builder().value(BigInteger.TEN).build();
    }
}
package org.purpleBean.kmip.codec.xml.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.P;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationSuite;

import java.math.BigInteger;

@DisplayName("P XML Serialization Tests")
class PXmlTest extends AbstractXmlSerializationSuite<P> {

    @Override
    protected Class<P> type() {
        return P.class;
    }

    @Override
    protected P createDefault() {
        return P.builder().value(BigInteger.ONE).build();
    }

    @Override
    protected P createVariant() {
        return P.builder().value(BigInteger.TEN).build();
    }
}
package org.purpleBean.kmip.codec.xml.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.G;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

import java.math.BigInteger;

@DisplayName("G XML Serialization Tests")
class GXmlTest extends AbstractXmlSerializationTestSuite<G> {

    @Override
    protected Class<G> type() {
        return G.class;
    }

    @Override
    protected G createDefault() {
        return G.builder().value(BigInteger.ONE).build();
    }

    @Override
    protected G createVariant() {
        return G.builder().value(BigInteger.TEN).build();
    }
}
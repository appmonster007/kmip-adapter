package org.purpleBean.kmip.codec.xml.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.G;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

import java.math.BigInteger;

@DisplayName("G XML Serialization Tests")
class GXmlTest extends AbstractXmlSerializationTestSuite<G> {

    @Override
    public Class<G> type() {
        return G.class;
    }

    @Override
    public G createDefault() {
        return G.builder().value(BigInteger.ONE).build();
    }

    @Override
    public G createVariant() {
        return G.builder().value(BigInteger.TEN).build();
    }
}
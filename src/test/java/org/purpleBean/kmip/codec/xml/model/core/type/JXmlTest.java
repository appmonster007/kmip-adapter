package org.purpleBean.kmip.codec.xml.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.J;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

import java.math.BigInteger;

@DisplayName("J XML Serialization Tests")
class JXmlTest extends AbstractXmlSerializationTestSuite<J> {

    @Override
    public Class<J> type() {
        return J.class;
    }

    @Override
    public J createDefault() {
        return J.builder().value(BigInteger.ONE).build();
    }

    @Override
    public J createVariant() {
        return J.builder().value(BigInteger.TEN).build();
    }
}
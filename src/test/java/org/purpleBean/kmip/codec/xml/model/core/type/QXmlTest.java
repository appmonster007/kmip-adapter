package org.purpleBean.kmip.codec.xml.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.Q;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

import java.math.BigInteger;

@DisplayName("Q XML Serialization Tests")
class QXmlTest extends AbstractXmlSerializationTestSuite<Q> {

    @Override
    public Class<Q> type() {
        return Q.class;
    }

    @Override
    public Q createDefault() {
        return Q.builder().value(BigInteger.ONE).build();
    }

    @Override
    public Q createVariant() {
        return Q.builder().value(BigInteger.TEN).build();
    }
}
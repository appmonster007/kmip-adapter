package org.purpleBean.kmip.codec.xml.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.Q;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

import java.math.BigInteger;

@DisplayName("Q XML Serialization Tests")
class QXmlTest extends AbstractXmlSerializationTestSuite<Q> {

    @Override
    protected Class<Q> type() {
        return Q.class;
    }

    @Override
    protected Q createDefault() {
        return Q.builder().value(BigInteger.ONE).build();
    }

    @Override
    protected Q createVariant() {
        return Q.builder().value(BigInteger.TEN).build();
    }
}
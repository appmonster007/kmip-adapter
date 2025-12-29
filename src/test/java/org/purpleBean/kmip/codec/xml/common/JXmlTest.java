package org.purpleBean.kmip.codec.xml.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.J;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationSuite;

import java.math.BigInteger;

@DisplayName("J XML Serialization Tests")
class JXmlTest extends AbstractXmlSerializationSuite<J> {

    @Override
    protected Class<J> type() {
        return J.class;
    }

    @Override
    protected J createDefault() {
        return J.builder().value(BigInteger.ONE).build();
    }

    @Override
    protected J createVariant() {
        return J.builder().value(BigInteger.TEN).build();
    }
}
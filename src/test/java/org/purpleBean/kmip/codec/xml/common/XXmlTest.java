package org.purpleBean.kmip.codec.xml.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.X;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

import java.math.BigInteger;

@DisplayName("X XML Serialization Tests")
class XXmlTest extends AbstractXmlSerializationTestSuite<X> {

    @Override
    protected Class<X> type() {
        return X.class;
    }

    @Override
    protected X createDefault() {
        return X.builder().value(BigInteger.ONE).build();
    }

    @Override
    protected X createVariant() {
        return X.builder().value(BigInteger.TEN).build();
    }
}
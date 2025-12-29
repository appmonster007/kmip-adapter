package org.purpleBean.kmip.codec.xml.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.Y;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationSuite;

import java.math.BigInteger;

@DisplayName("Y XML Serialization Tests")
class YXmlTest extends AbstractXmlSerializationSuite<Y> {

    @Override
    protected Class<Y> type() {
        return Y.class;
    }

    @Override
    protected Y createDefault() {
        return Y.builder().value(BigInteger.ONE).build();
    }

    @Override
    protected Y createVariant() {
        return Y.builder().value(BigInteger.TEN).build();
    }
}
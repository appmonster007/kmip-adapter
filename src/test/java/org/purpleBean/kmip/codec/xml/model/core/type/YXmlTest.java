package org.purpleBean.kmip.codec.xml.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.Y;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

import java.math.BigInteger;

@DisplayName("Y XML Serialization Tests")
class YXmlTest extends AbstractXmlSerializationTestSuite<Y> {

    @Override
    public Class<Y> type() {
        return Y.class;
    }

    @Override
    public Y createDefault() {
        return Y.builder().value(BigInteger.ONE).build();
    }

    @Override
    public Y createVariant() {
        return Y.builder().value(BigInteger.TEN).build();
    }
}
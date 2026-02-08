package org.purpleBean.kmip.codec.xml.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.X;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

import java.math.BigInteger;

@DisplayName("X XML Serialization Tests")
class XXmlTest extends AbstractXmlSerializationTestSuite<X> {

    @Override
    public Class<X> type() {
        return X.class;
    }

    @Override
    public X createDefault() {
        return X.builder().value(BigInteger.ONE).build();
    }

    @Override
    public X createVariant() {
        return X.builder().value(BigInteger.TEN).build();
    }
}
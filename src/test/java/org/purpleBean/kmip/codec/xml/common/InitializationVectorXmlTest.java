package org.purpleBean.kmip.codec.xml.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.InitializationVector;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("InitializationVector XML Serialization Tests")
class InitializationVectorXmlTest extends AbstractXmlSerializationTestSuite<InitializationVector> {

    @Override
    protected Class<InitializationVector> type() {
        return InitializationVector.class;
    }

    @Override
    protected InitializationVector createDefault() {
        return InitializationVector.of(new byte[]{0x01, 0x02, 0x03});
    }

    @Override
    protected InitializationVector createVariant() {
        return InitializationVector.of(new byte[]{0x04, 0x05, 0x06});
    }
}
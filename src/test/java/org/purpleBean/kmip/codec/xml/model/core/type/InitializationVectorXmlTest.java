package org.purpleBean.kmip.codec.xml.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.InitializationVector;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("InitializationVector XML Serialization Tests")
class InitializationVectorXmlTest extends AbstractXmlSerializationTestSuite<InitializationVector> {

    @Override
    public Class<InitializationVector> type() {
        return InitializationVector.class;
    }

    @Override
    public InitializationVector createDefault() {
        return InitializationVector.of(new byte[]{0x01, 0x02, 0x03});
    }

    @Override
    public InitializationVector createVariant() {
        return InitializationVector.of(new byte[]{0x04, 0x05, 0x06});
    }
}
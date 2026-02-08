package org.purpleBean.kmip.codec.xml.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.Salt;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("Salt XML Serialization Tests")
class SaltXmlTest extends AbstractXmlSerializationTestSuite<Salt> {

    @Override
    public Class<Salt> type() {
        return Salt.class;
    }

    @Override
    public Salt createDefault() {
        return Salt.of(new byte[]{0x01, 0x02, 0x03});
    }

    @Override
    public Salt createVariant() {
        return Salt.of(new byte[]{0x04, 0x05, 0x06});
    }
}
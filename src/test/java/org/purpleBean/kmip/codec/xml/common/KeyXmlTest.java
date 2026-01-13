package org.purpleBean.kmip.codec.xml.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.Key;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("Key XML Serialization Tests")
class KeyXmlTest extends AbstractXmlSerializationTestSuite<Key> {

    @Override
    protected Class<Key> type() {
        return Key.class;
    }

    @Override
    protected Key createDefault() {
        return Key.of(new byte[]{0x01, 0x02, 0x03});
    }

    @Override
    protected Key createVariant() {
        return Key.of(new byte[]{0x04, 0x05, 0x06});
    }
}
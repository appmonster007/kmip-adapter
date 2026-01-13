package org.purpleBean.kmip.codec.xml.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.KeyPartIdentifier;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("KeyPartIdentifier XML Serialization Tests")
class KeyPartIdentifierXmlTest extends AbstractXmlSerializationTestSuite<KeyPartIdentifier> {

    @Override
    protected Class<KeyPartIdentifier> type() {
        return KeyPartIdentifier.class;
    }

    @Override
    protected KeyPartIdentifier createDefault() {
        return KeyPartIdentifier.builder().value(1).build();
    }

    @Override
    protected KeyPartIdentifier createVariant() {
        return KeyPartIdentifier.builder().value(2).build();
    }
}
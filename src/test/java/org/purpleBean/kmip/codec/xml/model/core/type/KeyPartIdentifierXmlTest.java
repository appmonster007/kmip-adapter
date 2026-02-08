package org.purpleBean.kmip.codec.xml.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.KeyPartIdentifier;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("KeyPartIdentifier XML Serialization Tests")
class KeyPartIdentifierXmlTest extends AbstractXmlSerializationTestSuite<KeyPartIdentifier> {

    @Override
    public Class<KeyPartIdentifier> type() {
        return KeyPartIdentifier.class;
    }

    @Override
    public KeyPartIdentifier createDefault() {
        return KeyPartIdentifier.builder().value(1).build();
    }

    @Override
    public KeyPartIdentifier createVariant() {
        return KeyPartIdentifier.builder().value(2).build();
    }
}
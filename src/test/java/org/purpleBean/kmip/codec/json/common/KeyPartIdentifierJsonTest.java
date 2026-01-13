package org.purpleBean.kmip.codec.json.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.KeyPartIdentifier;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("KeyPartIdentifier JSON Serialization Tests")
class KeyPartIdentifierJsonTest extends AbstractJsonSerializationTestSuite<KeyPartIdentifier> {

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
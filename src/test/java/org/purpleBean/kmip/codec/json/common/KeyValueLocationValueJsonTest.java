package org.purpleBean.kmip.codec.json.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.KeyValueLocationValue;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("KeyValueLocationValue JSON Serialization Tests")
class KeyValueLocationValueJsonTest extends AbstractJsonSerializationTestSuite<KeyValueLocationValue> {

    @Override
    protected Class<KeyValueLocationValue> type() {
        return KeyValueLocationValue.class;
    }

    @Override
    protected KeyValueLocationValue createDefault() {
        return KeyValueLocationValue.builder().value("test").build();
    }

    @Override
    protected KeyValueLocationValue createVariant() {
        return KeyValueLocationValue.builder().value("test-2").build();
    }
}
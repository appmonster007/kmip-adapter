package org.purpleBean.kmip.codec.ttlv.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.KeyValueLocationValue;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("KeyValueLocationValue TTLV Serialization Tests")
class KeyValueLocationValueTtlvTest extends AbstractTtlvSerializationTestSuite<KeyValueLocationValue> {

    @Override
    public Class<KeyValueLocationValue> type() {
        return KeyValueLocationValue.class;
    }

    @Override
    public KeyValueLocationValue createDefault() {
        return KeyValueLocationValue.builder().value("test").build();
    }

    @Override
    public KeyValueLocationValue createVariant() {
        return KeyValueLocationValue.builder().value("test-2").build();
    }
}
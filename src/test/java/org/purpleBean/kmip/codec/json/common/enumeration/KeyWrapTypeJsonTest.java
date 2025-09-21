package org.purpleBean.kmip.codec.json.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.KeyWrapType;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationSuite;

@DisplayName("KeyWrapType JSON Serialization")
class KeyWrapTypeJsonTest extends AbstractJsonSerializationSuite<KeyWrapType> {
    @Override
    protected Class<KeyWrapType> type() {
        return KeyWrapType.class;
    }

    @Override
    protected KeyWrapType createDefault() {
        return new KeyWrapType(KeyWrapType.Standard.NOT_WRAPPED);
    }

    @Override
    protected KeyWrapType createVariant() {
        return new KeyWrapType(KeyWrapType.Standard.AS_REGISTERED);
    }
}

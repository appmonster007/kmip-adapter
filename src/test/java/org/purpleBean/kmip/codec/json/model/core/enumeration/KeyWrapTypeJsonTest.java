package org.purpleBean.kmip.codec.json.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.enumeration.KeyWrapType;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("KeyWrapType JSON Serialization")
class KeyWrapTypeJsonTest extends AbstractJsonSerializationTestSuite<KeyWrapType> {
    @Override
    protected Class<KeyWrapType> type() {
        return KeyWrapType.class;
    }

    @Override
    protected KeyWrapType createDefault() {
        return KeyWrapType.Standard.NOT_WRAPPED.inst();
    }

    @Override
    protected KeyWrapType createVariant() {
        return KeyWrapType.Standard.AS_REGISTERED.inst();
    }
}

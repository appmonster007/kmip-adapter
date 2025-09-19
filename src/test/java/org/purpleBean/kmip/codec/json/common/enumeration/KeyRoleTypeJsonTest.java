package org.purpleBean.kmip.codec.json.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.KeyRoleType;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationSuite;

@DisplayName("KeyRoleType JSON Serialization")
class KeyRoleTypeJsonTest extends AbstractJsonSerializationSuite<KeyRoleType> {
    @Override
    protected Class<KeyRoleType> type() {
        return KeyRoleType.class;
    }

    @Override
    protected KeyRoleType createDefault() {
        return new KeyRoleType(KeyRoleType.Standard.BDK);
    }

    @Override
    protected KeyRoleType createVariant() {
        return new KeyRoleType(KeyRoleType.Standard.CVK);
    }
}

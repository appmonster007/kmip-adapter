package org.purpleBean.kmip.codec.ttlv.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.enumeration.KeyRoleType;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("KeyRoleType TTLV Serialization")
class KeyRoleTypeTtlvTest extends AbstractTtlvSerializationTestSuite<KeyRoleType> {
    @Override
    public Class<KeyRoleType> type() {
        return KeyRoleType.class;
    }

    @Override
    public KeyRoleType createDefault() {
        return KeyRoleType.Standard.BDK.inst();
    }

    @Override
    public KeyRoleType createVariant() {
        return KeyRoleType.Standard.CVK.inst();
    }
}

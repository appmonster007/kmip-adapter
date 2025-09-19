package org.purpleBean.kmip.codec.ttlv.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.KeyRoleType;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationSuite;

@DisplayName("KeyRoleType TTLV Serialization")
class KeyRoleTypeTtlvTest extends AbstractTtlvSerializationSuite<KeyRoleType> {
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

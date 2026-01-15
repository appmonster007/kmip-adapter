package org.purpleBean.kmip.codec.ttlv.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.KeyWrapType;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("KeyWrapType TTLV Serialization")
class KeyWrapTypeTtlvTest extends AbstractTtlvSerializationTestSuite<KeyWrapType> {
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

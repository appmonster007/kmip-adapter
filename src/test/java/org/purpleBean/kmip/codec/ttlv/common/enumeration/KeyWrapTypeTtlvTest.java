package org.purpleBean.kmip.codec.ttlv.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.KeyWrapType;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationSuite;

@DisplayName("KeyWrapType TTLV Serialization")
class KeyWrapTypeTtlvTest extends AbstractTtlvSerializationSuite<KeyWrapType> {
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

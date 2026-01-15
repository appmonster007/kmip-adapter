package org.purpleBean.kmip.codec.ttlv.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.SecretDataType;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("SecretDataType TTLV Serialization")
class SecretDataTypeTtlvTest extends AbstractTtlvSerializationTestSuite<SecretDataType> {
    @Override
    protected Class<SecretDataType> type() {
        return SecretDataType.class;
    }

    @Override
    protected SecretDataType createDefault() {
        return SecretDataType.Standard.PASSWORD.inst();
    }

    @Override
    protected SecretDataType createVariant() {
        return SecretDataType.Standard.SEED.inst();
    }
}

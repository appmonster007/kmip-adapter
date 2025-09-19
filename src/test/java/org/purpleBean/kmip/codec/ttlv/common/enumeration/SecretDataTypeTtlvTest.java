package org.purpleBean.kmip.codec.ttlv.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.SecretDataType;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationSuite;

@DisplayName("SecretDataType TTLV Serialization")
class SecretDataTypeTtlvTest extends AbstractTtlvSerializationSuite<SecretDataType> {
    @Override
    protected Class<SecretDataType> type() {
        return SecretDataType.class;
    }

    @Override
    protected SecretDataType createDefault() {
        return new SecretDataType(SecretDataType.Standard.PASSWORD);
    }

    @Override
    protected SecretDataType createVariant() {
        return new SecretDataType(SecretDataType.Standard.SEED);
    }
}

package org.purpleBean.kmip.codec.json.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.SecretDataType;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationSuite;

@DisplayName("SecretDataType JSON Serialization")
class SecretDataTypeJsonTest extends AbstractJsonSerializationSuite<SecretDataType> {
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

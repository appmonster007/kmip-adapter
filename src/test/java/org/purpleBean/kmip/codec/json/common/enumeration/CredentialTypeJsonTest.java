package org.purpleBean.kmip.codec.json.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.CredentialType;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("CredentialType JSON Serialization")
class CredentialTypeJsonTest extends AbstractJsonSerializationTestSuite<CredentialType> {
    @Override
    protected Class<CredentialType> type() {
        return CredentialType.class;
    }

    @Override
    protected CredentialType createDefault() {
        return CredentialType.Standard.USERNAME_AND_PASSWORD.inst();
    }

    @Override
    protected CredentialType createVariant() {
        return CredentialType.Standard.DEVICE.inst();
    }
}

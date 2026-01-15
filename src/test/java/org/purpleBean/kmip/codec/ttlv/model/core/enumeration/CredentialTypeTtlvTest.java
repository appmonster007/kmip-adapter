package org.purpleBean.kmip.codec.ttlv.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.enumeration.CredentialType;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("CredentialType TTLV Serialization")
class CredentialTypeTtlvTest extends AbstractTtlvSerializationTestSuite<CredentialType> {
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

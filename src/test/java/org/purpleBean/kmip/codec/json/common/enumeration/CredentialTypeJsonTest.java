package org.purpleBean.kmip.codec.json.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.CredentialType;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationSuite;

@DisplayName("CredentialType JSON Serialization")
class CredentialTypeJsonTest extends AbstractJsonSerializationSuite<CredentialType> {
    @Override
    protected Class<CredentialType> type() {
        return CredentialType.class;
    }

    @Override
    protected CredentialType createDefault() {
        return new CredentialType(CredentialType.Standard.PLACEHOLDER_1);
    }

    @Override
    protected CredentialType createVariant() {
        return new CredentialType(CredentialType.Standard.PLACEHOLDER_2);
    }
}

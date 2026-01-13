package org.purpleBean.kmip.codec.xml.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.CredentialType;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("CredentialType XML Serialization")
class CredentialTypeXmlTest extends AbstractXmlSerializationTestSuite<CredentialType> {
    @Override
    protected Class<CredentialType> type() {
        return CredentialType.class;
    }

    @Override
    protected CredentialType createDefault() {
        return new CredentialType(CredentialType.Standard.USERNAME_AND_PASSWORD);
    }

    @Override
    protected CredentialType createVariant() {
        return new CredentialType(CredentialType.Standard.DEVICE);
    }
}

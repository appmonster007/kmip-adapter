package org.purpleBean.kmip.codec.xml.model.core.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.enumeration.CredentialType;
import org.purpleBean.kmip.model.core.structure.Credential;
import org.purpleBean.kmip.model.core.structure.UsernameAndPassword;
import org.purpleBean.kmip.model.core.type.Password;
import org.purpleBean.kmip.model.core.type.Username;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("Credential Xml Serialization Tests")
class CredentialXmlTest extends AbstractXmlSerializationTestSuite<Credential> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.V1_2;
    }

    @Override
    public Class<Credential> type() {
        return Credential.class;
    }

    @Override
    public Credential createDefault() {
        return Credential.builder()
                .credentialType(CredentialType.Standard.USERNAME_AND_PASSWORD.inst())
                .credentialValue(UsernameAndPassword.builder()
                        .username(Username.of("test-user"))
                        .password(Password.of("test-password"))
                        .build())
                .build();
    }

    @Override
    public Credential createVariant() {
        return Credential.builder()
                .credentialType(CredentialType.Standard.USERNAME_AND_PASSWORD.inst())
                .credentialValue(UsernameAndPassword.builder()
                        .username(Username.of("test-user-variant"))
                        .password(Password.of("test-password-variant"))
                        .build())
                .build();
    }
}
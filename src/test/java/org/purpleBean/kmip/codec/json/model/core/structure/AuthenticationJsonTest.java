package org.purpleBean.kmip.codec.json.model.core.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.enumeration.CredentialType;
import org.purpleBean.kmip.model.core.structure.Authentication;
import org.purpleBean.kmip.model.core.structure.Credential;
import org.purpleBean.kmip.model.core.structure.UsernameAndPassword;
import org.purpleBean.kmip.model.core.type.Password;
import org.purpleBean.kmip.model.core.type.Username;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("Authentication Json Serialization Tests")
class AuthenticationJsonTest extends AbstractJsonSerializationTestSuite<Authentication> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.V1_2;
    }

    @Override
    public Class<Authentication> type() {
        return Authentication.class;
    }

    @Override
    public Authentication createDefault() {
        Credential credential = Credential.builder()
                .credentialType(CredentialType.Standard.USERNAME_AND_PASSWORD.inst())
                .credentialValue(UsernameAndPassword.builder()
                        .username(Username.of("test-user"))
                        .password(Password.of("test-password"))
                        .build())
                .build();
        return Authentication.builder()
                .credential(credential)
                .credential(credential)
                .build();
    }

    @Override
    public Authentication createVariant() {
        Credential credential = Credential.builder()
                .credentialType(CredentialType.Standard.USERNAME_AND_PASSWORD.inst())
                .credentialValue(UsernameAndPassword.builder()
                        .username(Username.of("test-user-variant"))
                        .password(Password.of("test-password-variant"))
                        .build())
                .build();
        return Authentication.builder()
                .credential(credential)
                .credential(credential)
                .build();
    }
}
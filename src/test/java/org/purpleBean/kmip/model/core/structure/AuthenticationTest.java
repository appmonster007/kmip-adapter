package org.purpleBean.kmip.model.core.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.enumeration.CredentialType;
import org.purpleBean.kmip.model.core.type.Password;
import org.purpleBean.kmip.model.core.type.Username;
import org.purpleBean.kmip.test.suite.AbstractKmipStructureTestSuite;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Authentication Domain Tests")
class AuthenticationTest extends AbstractKmipStructureTestSuite<Authentication> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.UnknownVersion;
    }

    @Override
    protected Class<Authentication> type() {
        return Authentication.class;
    }

    @Override
    protected Authentication createDefault() {
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
    protected EncodingType expectedEncodingType() {
        return EncodingType.STRUCTURE;
    }

    @Override
    protected int expectedMinComponentCount() {
        return 0;
    }

    @Override
    protected void validateComponents(List<KmipDataType> values) {
        assertThat(values).hasSize(2);
        assertThat(values.getFirst()).isInstanceOf(Credential.class);
    }
}
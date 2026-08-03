package org.purplebean.kmip.model.core.structure;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.api.KmipDataType;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.model.core.enumeration.CredentialType;
import org.purplebean.kmip.model.core.type.Password;
import org.purplebean.kmip.model.core.type.Username;
import org.purplebean.kmip.test.suite.AbstractKmipStructureTestSuite;

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
    Credential credential = Credential
        .builder()
        .credentialType(CredentialType.Standard.USERNAME_AND_PASSWORD.inst())
        .credentialValue(UsernameAndPassword
            .builder()
            .username(Username.of("test-user"))
            .password(Password.of("test-password"))
            .build())
        .build();
    return Authentication
        .builder()
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
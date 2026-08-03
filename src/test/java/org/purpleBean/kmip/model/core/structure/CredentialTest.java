package org.purpleBean.kmip.model.core.structure;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.enumeration.CredentialType;
import org.purpleBean.kmip.model.core.type.Password;
import org.purpleBean.kmip.model.core.type.Username;
import org.purpleBean.kmip.test.suite.AbstractKmipStructureTestSuite;

@DisplayName("Credential Domain Tests")
class CredentialTest extends AbstractKmipStructureTestSuite<Credential> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.UnknownVersion;
  }

  @Override
  protected Class<Credential> type() {
    return Credential.class;
  }

  @Override
  protected Credential createDefault() {
    return Credential
        .builder()
        .credentialType(CredentialType.Standard.USERNAME_AND_PASSWORD.inst())
        .credentialValue(UsernameAndPassword
            .builder()
            .username(Username.of("test-user"))
            .password(Password.of("test-password"))
            .build())
        .build();
  }

  @Override
  protected EncodingType expectedEncodingType() {
    return EncodingType.STRUCTURE;
  }

  @Override
  protected int expectedMinComponentCount() {
    return 2;
  }

  @Override
  protected void validateComponents(List<KmipDataType> values) {
    assertThat(values).hasSize(2);
    assertThat(values.get(0)).isInstanceOf(CredentialType.class);
    assertThat(values.get(1)).isInstanceOf(UsernameAndPassword.class);
  }
}
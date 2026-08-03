package org.purpleBean.kmip.model.v3_0.structure;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.v3_0.type.HashedPasswordUsername;
import org.purpleBean.kmip.model.v3_0.type.HashedUsernamePassword;
import org.purpleBean.kmip.test.suite.AbstractKmipStructureTestSuite;

@DisplayName("HashedPasswordCredential Domain Tests")
class HashedPasswordCredentialTest
    extends AbstractKmipStructureTestSuite<HashedPasswordCredential> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.UnknownVersion;
  }

  @Override
  protected Class<HashedPasswordCredential> type() {
    return HashedPasswordCredential.class;
  }

  @Override
  protected HashedPasswordCredential createDefault() {
    return HashedPasswordCredential
        .builder()
        .hashedUsernamePassword(HashedUsernamePassword.of(new byte[] {0x01, 0x02, 0x03}))
        .hashedPasswordUsername(HashedPasswordUsername.of(new byte[] {0x04, 0x05, 0x06}))
        .build();
  }

  @Override
  protected EncodingType expectedEncodingType() {
    return EncodingType.STRUCTURE;
  }

  @Override
  public int expectedMinComponentCount() {
    return 2;
  }

  @Override
  public void validateComponents(List<KmipDataType> values) {
    assertThat(values.size()).isGreaterThanOrEqualTo(2);
    assertThat(values.get(0)).isInstanceOf(HashedUsernamePassword.class);
    assertThat(values.get(1)).isInstanceOf(HashedPasswordUsername.class);
  }
}
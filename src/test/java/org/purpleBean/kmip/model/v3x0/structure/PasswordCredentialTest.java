package org.purpleBean.kmip.model.v3x0.structure;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.type.Password;
import org.purpleBean.kmip.test.suite.AbstractKmipStructureTestSuite;

@DisplayName("PasswordCredential Domain Tests")
class PasswordCredentialTest extends AbstractKmipStructureTestSuite<PasswordCredential> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.UnknownVersion;
  }

  @Override
  protected Class<PasswordCredential> type() {
    return PasswordCredential.class;
  }

  @Override
  protected PasswordCredential createDefault() {
    return PasswordCredential
        .builder()
        .password(Password.of("s3cr3t"))
        .build();
  }

  @Override
  protected EncodingType expectedEncodingType() {
    return EncodingType.STRUCTURE;
  }

  @Override
  public int expectedMinComponentCount() {
    return 1;
  }

  @Override
  public void validateComponents(List<KmipDataType> values) {
    assertThat(values).hasSize(1);
    assertThat(values.get(0)).isInstanceOf(Password.class);
  }
}
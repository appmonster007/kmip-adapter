package org.purpleBean.kmip.model.v3_0.structure;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.enumeration.CredentialType;
import org.purpleBean.kmip.test.suite.AbstractKmipStructureTestSuite;

@DisplayName("CredentialInformation Domain Tests")
class CredentialInformationTest extends AbstractKmipStructureTestSuite<CredentialInformation> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.UnknownVersion;
  }

  @Override
  protected Class<CredentialInformation> type() {
    return CredentialInformation.class;
  }

  @Override
  protected CredentialInformation createDefault() {
    return CredentialInformation
        .builder()
        .credentialType(CredentialType.of(CredentialType.Standard.USERNAME_AND_PASSWORD))
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
    assertThat(values.get(0)).isInstanceOf(CredentialType.class);
  }
}
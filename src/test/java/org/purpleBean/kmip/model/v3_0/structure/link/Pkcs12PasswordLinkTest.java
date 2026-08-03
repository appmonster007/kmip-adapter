package org.purpleBean.kmip.model.v3_0.structure.link;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.test.suite.AbstractKmipStructureTestSuite;

@DisplayName("Pkcs12PasswordLink Domain Tests")
class Pkcs12PasswordLinkTest extends AbstractKmipStructureTestSuite<Pkcs12PasswordLink> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V3_0;
  }

  @Override
  protected Class<Pkcs12PasswordLink> type() {
    return Pkcs12PasswordLink.class;
  }

  @Override
  protected Pkcs12PasswordLink createDefault() {
    // TODO: Create a default instance of the structure
    return Pkcs12PasswordLink.of(UniqueIdentifier.of("test-id"));
  }

  @Override
  protected EncodingType expectedEncodingType() {
    return EncodingType.STRUCTURE;
  }

  @Override
  public int expectedMinComponentCount() {
    // TODO: Set the expected minimum number of components
    return 1;
  }

  @Override
  public void validateComponents(List<KmipDataType> values) {
    // TODO: Validate the components of the structure
    assertThat(values).hasSize(1);
  }
}
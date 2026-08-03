package org.purplebean.kmip.model.core.structure;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.api.KmipDataType;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.model.core.type.Username;
import org.purplebean.kmip.test.suite.AbstractKmipStructureTestSuite;

@DisplayName("CredentialValueGenericStructure Domain Tests")
class CredentialValueGenericStructureTest
    extends AbstractKmipStructureTestSuite<CredentialValueGenericStructure> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.UnknownVersion;
  }

  @Override
  protected Class<CredentialValueGenericStructure> type() {
    return CredentialValueGenericStructure.class;
  }

  @Override
  protected CredentialValueGenericStructure createDefault() {
    return CredentialValueGenericStructure
        .builder()
        .value(Username.of("test-value"))
        .build();
  }

  @Override
  protected EncodingType expectedEncodingType() {
    return EncodingType.STRUCTURE;
  }

  @Override
  protected int expectedMinComponentCount() {
    return 1;
  }

  @Override
  protected void validateComponents(List<KmipDataType> values) {
    assertThat(values).hasSize(1);
    assertThat(values.getFirst()).isInstanceOf(Username.class);
  }
}
package org.purpleBean.kmip.model.v3_0.structure.link;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.test.suite.AbstractKmipStructureTestSuite;

@DisplayName("WrappingKeyLink Domain Tests")
class WrappingKeyLinkTest extends AbstractKmipStructureTestSuite<WrappingKeyLink> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V3_0;
  }

  @Override
  protected Class<WrappingKeyLink> type() {
    return WrappingKeyLink.class;
  }

  @Override
  protected WrappingKeyLink createDefault() {
    // TODO: Create a default instance of the structure
    return WrappingKeyLink.of(UniqueIdentifier.of("test-id"));
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
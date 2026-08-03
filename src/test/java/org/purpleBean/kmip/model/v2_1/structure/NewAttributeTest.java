package org.purpleBean.kmip.model.v2_1.structure;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.test.suite.AbstractKmipStructureTestSuite;

@DisplayName("NewAttribute Domain Tests")
class NewAttributeTest extends AbstractKmipStructureTestSuite<NewAttribute> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.UnknownVersion;
  }

  @Override
  protected Class<NewAttribute> type() {
    return NewAttribute.class;
  }

  @Override
  public NewAttribute createDefault() {
    return NewAttribute
        .builder()
        .attribute(org.purpleBean.kmip.model.core.type.UniqueIdentifier.of("test-uid"))
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
  }
}
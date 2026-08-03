package org.purplebean.kmip.model.v2x1.structure;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.api.KmipDataType;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.test.suite.AbstractKmipStructureTestSuite;

@DisplayName("CurrentAttribute Domain Tests")
class CurrentAttributeTest extends AbstractKmipStructureTestSuite<CurrentAttribute> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.UnknownVersion;
  }

  @Override
  protected Class<CurrentAttribute> type() {
    return CurrentAttribute.class;
  }

  @Override
  public CurrentAttribute createDefault() {
    return CurrentAttribute
        .builder()
        .attribute(org.purplebean.kmip.model.core.type.UniqueIdentifier.of("test-uid"))
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
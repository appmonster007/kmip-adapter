package org.purplebean.kmip.model.core.structure;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.api.KmipDataType;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.model.core.enumeration.WrappingMethod;
import org.purplebean.kmip.test.suite.AbstractKmipStructureTestSuite;

@DisplayName("KeyWrappingData Domain Tests")
class KeyWrappingDataTest extends AbstractKmipStructureTestSuite<KeyWrappingData> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V1_2;
  }

  @Override
  protected Class<KeyWrappingData> type() {
    return KeyWrappingData.class;
  }

  @Override
  protected KeyWrappingData createDefault() {
    return KeyWrappingData
        .builder()
        .wrappingMethod(WrappingMethod.Standard.ENCRYPT.inst())
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
    assertThat(values.getFirst()).isInstanceOf(WrappingMethod.class);
  }
}
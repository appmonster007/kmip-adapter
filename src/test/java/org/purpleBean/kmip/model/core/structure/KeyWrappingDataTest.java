package org.purpleBean.kmip.model.core.structure;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.enumeration.WrappingMethod;
import org.purpleBean.kmip.test.suite.AbstractKmipStructureTestSuite;

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
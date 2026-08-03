package org.purpleBean.kmip.codec.ttlv.model.v2x1.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2x1.structure.Objects;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("Objects Ttlv Serialization Tests")
class ObjectsTtlvTest extends AbstractTtlvSerializationTestSuite<Objects> {

  @Override
  public Class<Objects> type() {
    return Objects.class;
  }

  @Override
  public Objects createDefault() {
    return Objects
        .builder()
        .build();
  }

  @Override
  public Objects createVariant() {
    return Objects
        .builder()
        .build();
  }
}
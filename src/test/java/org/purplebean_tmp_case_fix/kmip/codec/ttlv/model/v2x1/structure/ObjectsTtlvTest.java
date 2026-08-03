package org.purplebean.kmip.codec.ttlv.model.v2x1.structure;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.v2x1.structure.Objects;
import org.purplebean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

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
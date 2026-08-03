package org.purplebean.kmip.codec.ttlv.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.type.IterationCount;
import org.purplebean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("IterationCount TTLV Serialization Tests")
class IterationCountTtlvTest extends AbstractTtlvSerializationTestSuite<IterationCount> {

  @Override
  public Class<IterationCount> type() {
    return IterationCount.class;
  }

  @Override
  public IterationCount createDefault() {
    return IterationCount
        .builder()
        .value(1000)
        .build();
  }

  @Override
  public IterationCount createVariant() {
    return IterationCount
        .builder()
        .value(2000)
        .build();
  }
}
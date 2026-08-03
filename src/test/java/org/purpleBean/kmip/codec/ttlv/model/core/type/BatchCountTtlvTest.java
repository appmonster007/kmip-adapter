package org.purpleBean.kmip.codec.ttlv.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.BatchCount;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("BatchCount TTLV Serialization Tests")
class BatchCountTtlvTest extends AbstractTtlvSerializationTestSuite<BatchCount> {

  @Override
  public Class<BatchCount> type() {
    return BatchCount.class;
  }

  @Override
  public BatchCount createDefault() {
    return BatchCount
        .builder()
        .value(5)
        .build();
  }

  @Override
  public BatchCount createVariant() {
    return BatchCount
        .builder()
        .value(10)
        .build();
  }
}
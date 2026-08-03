package org.purpleBean.kmip.codec.ttlv.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.MaximumResponseSize;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("MaximumResponseSize TTLV Serialization Tests")
class MaximumResponseSizeTtlvTest extends AbstractTtlvSerializationTestSuite<MaximumResponseSize> {

  @Override
  public Class<MaximumResponseSize> type() {
    return MaximumResponseSize.class;
  }

  @Override
  public MaximumResponseSize createDefault() {
    return MaximumResponseSize
        .builder()
        .value(1024)
        .build();
  }

  @Override
  public MaximumResponseSize createVariant() {
    return MaximumResponseSize
        .builder()
        .value(2048)
        .build();
  }
}
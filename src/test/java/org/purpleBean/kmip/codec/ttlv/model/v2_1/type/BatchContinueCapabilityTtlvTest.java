package org.purpleBean.kmip.codec.ttlv.model.v2_1.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2_1.type.BatchContinueCapability;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("BatchContinueCapability Ttlv Serialization Tests")
class BatchContinueCapabilityTtlvTest
    extends AbstractTtlvSerializationTestSuite<BatchContinueCapability> {

  @Override
  public Class<BatchContinueCapability> type() {
    return BatchContinueCapability.class;
  }

  @Override
  public BatchContinueCapability createDefault() {
    return BatchContinueCapability.of(true);
  }

  @Override
  public BatchContinueCapability createVariant() {
    return BatchContinueCapability.of(false);
  }
}
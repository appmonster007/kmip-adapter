package org.purplebean.kmip.codec.ttlv.model.v2x1.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.v2x1.type.BatchContinueCapability;
import org.purplebean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

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
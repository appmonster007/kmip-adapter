package org.purpleBean.kmip.codec.ttlv.model.v2x1.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2x1.type.BatchUndoCapability;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("BatchUndoCapability Ttlv Serialization Tests")
class BatchUndoCapabilityTtlvTest extends AbstractTtlvSerializationTestSuite<BatchUndoCapability> {

  @Override
  public Class<BatchUndoCapability> type() {
    return BatchUndoCapability.class;
  }

  @Override
  public BatchUndoCapability createDefault() {
    return BatchUndoCapability.of(true);
  }

  @Override
  public BatchUndoCapability createVariant() {
    return BatchUndoCapability.of(false);
  }
}
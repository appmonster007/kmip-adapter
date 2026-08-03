package org.purplebean.kmip.codec.json.model.v2x1.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.v2x1.type.BatchUndoCapability;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("BatchUndoCapability Json Serialization Tests")
class BatchUndoCapabilityJsonTest extends AbstractJsonSerializationTestSuite<BatchUndoCapability> {

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
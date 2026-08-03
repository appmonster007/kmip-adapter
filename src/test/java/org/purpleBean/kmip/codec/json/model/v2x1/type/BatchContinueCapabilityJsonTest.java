package org.purpleBean.kmip.codec.json.model.v2x1.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2x1.type.BatchContinueCapability;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("BatchContinueCapability Json Serialization Tests")
class BatchContinueCapabilityJsonTest
    extends AbstractJsonSerializationTestSuite<BatchContinueCapability> {

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
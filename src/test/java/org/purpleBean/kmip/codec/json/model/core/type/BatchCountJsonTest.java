package org.purplebean.kmip.codec.json.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.type.BatchCount;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("BatchCount JSON Serialization Tests")
class BatchCountJsonTest extends AbstractJsonSerializationTestSuite<BatchCount> {

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
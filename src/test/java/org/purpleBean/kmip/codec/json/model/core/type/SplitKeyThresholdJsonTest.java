package org.purplebean.kmip.codec.json.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.type.SplitKeyThreshold;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("SplitKeyThreshold JSON Serialization Tests")
class SplitKeyThresholdJsonTest extends AbstractJsonSerializationTestSuite<SplitKeyThreshold> {

  @Override
  public Class<SplitKeyThreshold> type() {
    return SplitKeyThreshold.class;
  }

  @Override
  public SplitKeyThreshold createDefault() {
    return SplitKeyThreshold
        .builder()
        .value(2)
        .build();
  }

  @Override
  public SplitKeyThreshold createVariant() {
    return SplitKeyThreshold
        .builder()
        .value(3)
        .build();
  }
}
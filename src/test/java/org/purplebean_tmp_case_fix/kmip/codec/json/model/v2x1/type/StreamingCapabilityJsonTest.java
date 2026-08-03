package org.purplebean.kmip.codec.json.model.v2x1.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.v2x1.type.StreamingCapability;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("StreamingCapability Json Serialization Tests")
class StreamingCapabilityJsonTest extends AbstractJsonSerializationTestSuite<StreamingCapability> {

  @Override
  public Class<StreamingCapability> type() {
    return StreamingCapability.class;
  }

  @Override
  public StreamingCapability createDefault() {
    return StreamingCapability.of(true);
  }

  @Override
  public StreamingCapability createVariant() {
    return StreamingCapability.of(false);
  }
}
package org.purpleBean.kmip.codec.json.model.v2_1.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2_1.type.StreamingCapability;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

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
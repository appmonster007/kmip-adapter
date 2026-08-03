package org.purpleBean.kmip.codec.ttlv.model.v2x1.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2x1.type.StreamingCapability;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("StreamingCapability Ttlv Serialization Tests")
class StreamingCapabilityTtlvTest extends AbstractTtlvSerializationTestSuite<StreamingCapability> {

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
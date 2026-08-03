package org.purplebean.kmip.codec.ttlv.model.v2x1.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.v2x1.type.AsynchronousCapability;
import org.purplebean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("AsynchronousCapability Ttlv Serialization Tests")
class AsynchronousCapabilityTtlvTest
    extends AbstractTtlvSerializationTestSuite<AsynchronousCapability> {

  @Override
  public Class<AsynchronousCapability> type() {
    return AsynchronousCapability.class;
  }

  @Override
  public AsynchronousCapability createDefault() {
    return AsynchronousCapability.of(true);
  }

  @Override
  public AsynchronousCapability createVariant() {
    return AsynchronousCapability.of(false);
  }
}
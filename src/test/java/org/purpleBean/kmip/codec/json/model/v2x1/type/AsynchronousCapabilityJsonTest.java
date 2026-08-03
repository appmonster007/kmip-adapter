package org.purpleBean.kmip.codec.json.model.v2x1.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2x1.type.AsynchronousCapability;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("AsynchronousCapability Json Serialization Tests")
class AsynchronousCapabilityJsonTest
    extends AbstractJsonSerializationTestSuite<AsynchronousCapability> {

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
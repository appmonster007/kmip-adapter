package org.purpleBean.kmip.codec.xml.model.v2_1.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2_1.type.AsynchronousCapability;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("AsynchronousCapability Xml Serialization Tests")
class AsynchronousCapabilityXmlTest
    extends AbstractXmlSerializationTestSuite<AsynchronousCapability> {

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
package org.purplebean.kmip.codec.xml.model.v2x1.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.v2x1.type.BatchContinueCapability;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("BatchContinueCapability Xml Serialization Tests")
class BatchContinueCapabilityXmlTest
    extends AbstractXmlSerializationTestSuite<BatchContinueCapability> {

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
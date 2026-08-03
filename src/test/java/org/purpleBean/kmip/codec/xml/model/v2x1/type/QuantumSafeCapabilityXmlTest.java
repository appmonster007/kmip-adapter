package org.purplebean.kmip.codec.xml.model.v2x1.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.v2x1.type.QuantumSafeCapability;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("QuantumSafeCapability Xml Serialization Tests")
class QuantumSafeCapabilityXmlTest
    extends AbstractXmlSerializationTestSuite<QuantumSafeCapability> {

  @Override
  public Class<QuantumSafeCapability> type() {
    return QuantumSafeCapability.class;
  }

  @Override
  public QuantumSafeCapability createDefault() {
    return QuantumSafeCapability.of(true);
  }

  @Override
  public QuantumSafeCapability createVariant() {
    return QuantumSafeCapability.of(false);
  }
}
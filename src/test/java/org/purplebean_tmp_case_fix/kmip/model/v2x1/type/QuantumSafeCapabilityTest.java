package org.purplebean.kmip.model.v2x1.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.test.suite.AbstractKmipDataTypeTestSuite;


@DisplayName("QuantumSafeCapability Domain Tests")
class QuantumSafeCapabilityTest extends AbstractKmipDataTypeTestSuite<QuantumSafeCapability> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.UnknownVersion;
  }

  @Override
  protected Class<QuantumSafeCapability> type() {
    return QuantumSafeCapability.class;
  }

  @Override
  protected QuantumSafeCapability createDefault() {
    return QuantumSafeCapability.of(true);
  }

  @Override
  protected EncodingType expectedEncodingType() {
    return EncodingType.BOOLEAN;
  }
}
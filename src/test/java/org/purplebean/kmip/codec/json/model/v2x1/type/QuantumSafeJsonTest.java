package org.purplebean.kmip.codec.json.model.v2x1.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.v2x1.type.QuantumSafe;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("QuantumSafe Json Serialization Tests")
class QuantumSafeJsonTest extends AbstractJsonSerializationTestSuite<QuantumSafe> {

  @Override
  public Class<QuantumSafe> type() {
    return QuantumSafe.class;
  }

  @Override
  public QuantumSafe createDefault() {
    return QuantumSafe.of(true);
  }

  @Override
  public QuantumSafe createVariant() {
    return QuantumSafe.of(false);
  }
}
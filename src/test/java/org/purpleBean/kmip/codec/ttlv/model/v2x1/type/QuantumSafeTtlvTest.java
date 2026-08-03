package org.purpleBean.kmip.codec.ttlv.model.v2x1.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2x1.type.QuantumSafe;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("QuantumSafe Ttlv Serialization Tests")
class QuantumSafeTtlvTest extends AbstractTtlvSerializationTestSuite<QuantumSafe> {

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
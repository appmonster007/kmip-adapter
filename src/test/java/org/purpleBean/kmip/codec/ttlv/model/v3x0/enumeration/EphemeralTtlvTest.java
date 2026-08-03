package org.purpleBean.kmip.codec.ttlv.model.v3x0.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v3x0.enumeration.Ephemeral;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("Ephemeral TTLV Serialization")
class EphemeralTtlvTest extends AbstractTtlvSerializationTestSuite<Ephemeral> {
  @Override
  public Class<Ephemeral> type() {
    return Ephemeral.class;
  }

  @Override
  public Ephemeral createDefault() {
    return Ephemeral.Standard.DATA.inst();
  }

  @Override
  public Ephemeral createVariant() {
    return Ephemeral.Standard.EMPTY.inst();
  }
}

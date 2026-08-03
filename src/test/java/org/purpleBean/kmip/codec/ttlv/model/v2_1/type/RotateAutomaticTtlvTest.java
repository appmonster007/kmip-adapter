package org.purpleBean.kmip.codec.ttlv.model.v2_1.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2_1.type.RotateAutomatic;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("RotateAutomatic Ttlv Serialization Tests")
class RotateAutomaticTtlvTest extends AbstractTtlvSerializationTestSuite<RotateAutomatic> {

  @Override
  public Class<RotateAutomatic> type() {
    return RotateAutomatic.class;
  }

  @Override
  public RotateAutomatic createDefault() {
    return RotateAutomatic.of(true);
  }

  @Override
  public RotateAutomatic createVariant() {
    return RotateAutomatic.of(false);
  }
}
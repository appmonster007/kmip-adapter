package org.purplebean.kmip.codec.ttlv.model.v2x1.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.v2x1.type.RotateAutomatic;
import org.purplebean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

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
package org.purplebean.kmip.codec.ttlv.model.v2x1.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.v2x1.type.RotateInterval;
import org.purplebean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("RotateInterval Ttlv Serialization Tests")
class RotateIntervalTtlvTest extends AbstractTtlvSerializationTestSuite<RotateInterval> {

  @Override
  public Class<RotateInterval> type() {
    return RotateInterval.class;
  }

  @Override
  public RotateInterval createDefault() {
    return RotateInterval.of(12345L);
  }

  @Override
  public RotateInterval createVariant() {
    return RotateInterval.of(54321L);
  }
}
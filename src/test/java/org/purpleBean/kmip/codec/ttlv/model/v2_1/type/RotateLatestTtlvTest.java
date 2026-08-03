package org.purpleBean.kmip.codec.ttlv.model.v2_1.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2_1.type.RotateLatest;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("RotateLatest Ttlv Serialization Tests")
class RotateLatestTtlvTest extends AbstractTtlvSerializationTestSuite<RotateLatest> {

  @Override
  public Class<RotateLatest> type() {
    return RotateLatest.class;
  }

  @Override
  public RotateLatest createDefault() {
    return RotateLatest.of(true);
  }

  @Override
  public RotateLatest createVariant() {
    return RotateLatest.of(false);
  }
}
package org.purpleBean.kmip.codec.ttlv.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.Offset;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("Offset TTLV Serialization Tests")
class OffsetTtlvTest extends AbstractTtlvSerializationTestSuite<Offset> {

  @Override
  public Class<Offset> type() {
    return Offset.class;
  }

  @Override
  public Offset createDefault() {
    return Offset
        .builder()
        .value(10)
        .build();
  }

  @Override
  public Offset createVariant() {
    return Offset
        .builder()
        .value(20)
        .build();
  }
}
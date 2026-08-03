package org.purplebean.kmip.codec.ttlv.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.type.CriticalityIndicator;
import org.purplebean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("CriticalityIndicator TTLV Serialization Tests")
class CriticalityIndicatorTtlvTest
    extends AbstractTtlvSerializationTestSuite<CriticalityIndicator> {

  @Override
  public Class<CriticalityIndicator> type() {
    return CriticalityIndicator.class;
  }

  @Override
  public CriticalityIndicator createDefault() {
    return CriticalityIndicator
        .builder()
        .value(true)
        .build();
  }

  @Override
  public CriticalityIndicator createVariant() {
    return CriticalityIndicator
        .builder()
        .value(false)
        .build();
  }
}
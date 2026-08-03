package org.purplebean.kmip.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.test.suite.AbstractKmipDataTypeTestSuite;

@DisplayName("CriticalityIndicator Domain Tests")
class CriticalityIndicatorTest extends AbstractKmipDataTypeTestSuite<CriticalityIndicator> {

  @Override
  protected Class<CriticalityIndicator> type() {
    return CriticalityIndicator.class;
  }

  @Override
  protected CriticalityIndicator createDefault() {
    return CriticalityIndicator
        .builder()
        .value(true)
        .build();
  }

  @Override
  protected EncodingType expectedEncodingType() {
    return EncodingType.BOOLEAN;
  }
}
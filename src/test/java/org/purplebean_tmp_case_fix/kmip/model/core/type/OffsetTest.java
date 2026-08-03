package org.purplebean.kmip.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.test.suite.AbstractKmipDataTypeTestSuite;

@DisplayName("Offset Domain Tests")
class OffsetTest extends AbstractKmipDataTypeTestSuite<Offset> {

  @Override
  protected Class<Offset> type() {
    return Offset.class;
  }

  @Override
  protected Offset createDefault() {
    return Offset
        .builder()
        .value(10)
        .build();
  }

  @Override
  protected EncodingType expectedEncodingType() {
    return EncodingType.INTERVAL;
  }
}
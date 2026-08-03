package org.purplebean.kmip.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.test.suite.AbstractKmipDataTypeTestSuite;

@DisplayName("Qlength Domain Tests")
class QlengthTest extends AbstractKmipDataTypeTestSuite<Qlength> {

  @Override
  protected Class<Qlength> type() {
    return Qlength.class;
  }

  @Override
  protected Qlength createDefault() {
    return Qlength
        .builder()
        .value(128)
        .build();
  }

  @Override
  protected EncodingType expectedEncodingType() {
    return EncodingType.INTEGER;
  }
}
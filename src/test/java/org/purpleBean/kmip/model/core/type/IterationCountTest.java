package org.purplebean.kmip.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.test.suite.AbstractKmipDataTypeTestSuite;

@DisplayName("IterationCount Domain Tests")
class IterationCountTest extends AbstractKmipDataTypeTestSuite<IterationCount> {

  @Override
  protected Class<IterationCount> type() {
    return IterationCount.class;
  }

  @Override
  protected IterationCount createDefault() {
    return IterationCount
        .builder()
        .value(1000)
        .build();
  }

  @Override
  protected EncodingType expectedEncodingType() {
    return EncodingType.INTEGER;
  }
}
package org.purplebean.kmip.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.test.suite.AbstractKmipDataTypeTestSuite;

@DisplayName("MaximumResponseSize Domain Tests")
class MaximumResponseSizeTest extends AbstractKmipDataTypeTestSuite<MaximumResponseSize> {

  @Override
  protected Class<MaximumResponseSize> type() {
    return MaximumResponseSize.class;
  }

  @Override
  protected MaximumResponseSize createDefault() {
    return MaximumResponseSize
        .builder()
        .value(1024)
        .build();
  }

  @Override
  protected EncodingType expectedEncodingType() {
    return EncodingType.INTEGER;
  }
}
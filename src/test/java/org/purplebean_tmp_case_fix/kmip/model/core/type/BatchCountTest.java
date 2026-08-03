package org.purplebean.kmip.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.test.suite.AbstractKmipDataTypeTestSuite;

@DisplayName("BatchCount Domain Tests")
class BatchCountTest extends AbstractKmipDataTypeTestSuite<BatchCount> {

  @Override
  protected Class<BatchCount> type() {
    return BatchCount.class;
  }

  @Override
  protected BatchCount createDefault() {
    return BatchCount
        .builder()
        .value(5)
        .build();
  }

  @Override
  protected EncodingType expectedEncodingType() {
    return EncodingType.INTEGER;
  }
}
package org.purplebean.kmip.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.test.suite.AbstractKmipDataTypeTestSuite;

@DisplayName("AsynchronousIndicator Domain Tests")
class AsynchronousIndicatorTest extends AbstractKmipDataTypeTestSuite<AsynchronousIndicator> {

  @Override
  protected Class<AsynchronousIndicator> type() {
    return AsynchronousIndicator.class;
  }

  @Override
  protected AsynchronousIndicator createDefault() {
    return AsynchronousIndicator
        .builder()
        .value(true)
        .build();
  }

  @Override
  protected EncodingType expectedEncodingType() {
    return EncodingType.BOOLEAN;
  }
}
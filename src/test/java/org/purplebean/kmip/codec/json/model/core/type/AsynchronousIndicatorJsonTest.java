package org.purplebean.kmip.codec.json.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.type.AsynchronousIndicator;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("AsynchronousIndicator JSON Serialization Tests")
class AsynchronousIndicatorJsonTest
    extends AbstractJsonSerializationTestSuite<AsynchronousIndicator> {

  @Override
  public Class<AsynchronousIndicator> type() {
    return AsynchronousIndicator.class;
  }

  @Override
  public AsynchronousIndicator createDefault() {
    return AsynchronousIndicator
        .builder()
        .value(true)
        .build();
  }

  @Override
  public AsynchronousIndicator createVariant() {
    return AsynchronousIndicator
        .builder()
        .value(false)
        .build();
  }
}
package org.purplebean.kmip.codec.json.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.type.AsynchronousCorrelationValue;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("AsynchronousCorrelationValue JSON Serialization Tests")
class AsynchronousCorrelationValueJsonTest
    extends AbstractJsonSerializationTestSuite<AsynchronousCorrelationValue> {

  @Override
  public Class<AsynchronousCorrelationValue> type() {
    return AsynchronousCorrelationValue.class;
  }

  @Override
  public AsynchronousCorrelationValue createDefault() {
    return AsynchronousCorrelationValue.of(new byte[] {0x01, 0x02, 0x03});
  }

  @Override
  public AsynchronousCorrelationValue createVariant() {
    return AsynchronousCorrelationValue.of(new byte[] {0x04, 0x05, 0x06});
  }
}
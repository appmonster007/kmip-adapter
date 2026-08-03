package org.purplebean.kmip.codec.json.model.v2x1.type;

import java.nio.ByteBuffer;
import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.v2x1.type.CorrelationValue;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("CorrelationValue Json Serialization Tests")
class CorrelationValueJsonTest extends AbstractJsonSerializationTestSuite<CorrelationValue> {

  @Override
  public Class<CorrelationValue> type() {
    return CorrelationValue.class;
  }

  @Override
  public CorrelationValue createDefault() {
    return CorrelationValue.of(ByteBuffer.wrap(new byte[] {0x01, 0x02, 0x03}));
  }

  @Override
  public CorrelationValue createVariant() {
    return CorrelationValue.of(ByteBuffer.wrap(new byte[] {0x04, 0x05, 0x06}));
  }
}
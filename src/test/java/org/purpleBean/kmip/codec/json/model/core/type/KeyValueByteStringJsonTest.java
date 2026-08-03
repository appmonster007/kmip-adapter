package org.purplebean.kmip.codec.json.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.type.KeyValueByteString;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("KeyValue.ByteString JSON Serialization Tests")
class KeyValueByteStringJsonTest extends AbstractJsonSerializationTestSuite<KeyValueByteString> {

  @Override
  public Class<KeyValueByteString> type() {
    return KeyValueByteString.class;
  }

  @Override
  public KeyValueByteString createDefault() {
    return KeyValueByteString.of(new byte[] {0x01, 0x02, 0x03});
  }

  @Override
  public KeyValueByteString createVariant() {
    return KeyValueByteString.of(new byte[] {0x04, 0x05, 0x06});
  }
}
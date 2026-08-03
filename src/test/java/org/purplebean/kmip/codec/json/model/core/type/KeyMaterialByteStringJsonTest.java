package org.purplebean.kmip.codec.json.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.type.KeyMaterialByteString;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("KeyMaterialByteString JSON Serialization Tests")
class KeyMaterialByteStringJsonTest
    extends AbstractJsonSerializationTestSuite<KeyMaterialByteString> {

  @Override
  public Class<KeyMaterialByteString> type() {
    return KeyMaterialByteString.class;
  }

  @Override
  public KeyMaterialByteString createDefault() {
    return KeyMaterialByteString.of(new byte[] {0x01, 0x02, 0x03});
  }

  @Override
  public KeyMaterialByteString createVariant() {
    return KeyMaterialByteString.of(new byte[] {0x04, 0x05, 0x06});
  }
}
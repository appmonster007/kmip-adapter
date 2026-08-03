package org.purpleBean.kmip.codec.xml.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.KeyMaterialByteString;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("KeyMaterial.ByteString XML Serialization Tests")
class KeyMaterialByteStringXmlTest
    extends AbstractXmlSerializationTestSuite<KeyMaterialByteString> {

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
package org.purpleBean.kmip.codec.xml.model.v2x1.type;

import java.nio.ByteBuffer;
import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2x1.type.ShortUniqueIdentifier;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("ShortUniqueIdentifier Xml Serialization Tests")
class ShortUniqueIdentifierXmlTest
    extends AbstractXmlSerializationTestSuite<ShortUniqueIdentifier> {

  @Override
  public Class<ShortUniqueIdentifier> type() {
    return ShortUniqueIdentifier.class;
  }

  @Override
  public ShortUniqueIdentifier createDefault() {
    return ShortUniqueIdentifier.of(ByteBuffer.wrap(new byte[] {0x01, 0x02, 0x03}));
  }

  @Override
  public ShortUniqueIdentifier createVariant() {
    return ShortUniqueIdentifier.of(ByteBuffer.wrap(new byte[] {0x04, 0x05, 0x06}));
  }
}
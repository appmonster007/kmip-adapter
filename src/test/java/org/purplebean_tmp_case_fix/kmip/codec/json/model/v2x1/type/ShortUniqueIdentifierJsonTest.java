package org.purplebean.kmip.codec.json.model.v2x1.type;

import java.nio.ByteBuffer;
import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.v2x1.type.ShortUniqueIdentifier;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("ShortUniqueIdentifier Json Serialization Tests")
class ShortUniqueIdentifierJsonTest
    extends AbstractJsonSerializationTestSuite<ShortUniqueIdentifier> {

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
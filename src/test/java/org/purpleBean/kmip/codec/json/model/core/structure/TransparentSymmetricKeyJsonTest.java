package org.purplebean.kmip.codec.json.model.core.structure;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.structure.TransparentSymmetricKey;
import org.purplebean.kmip.model.core.type.Key;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("TransparentSymmetricKey JSON Serialization Tests")
class TransparentSymmetricKeyJsonTest
    extends AbstractJsonSerializationTestSuite<TransparentSymmetricKey> {

  private static final OffsetDateTime FIXED_TIME =
      OffsetDateTime.of(2024, 1, 2, 3, 4, 5, 0, ZoneOffset.UTC);

  @Override
  public Class<TransparentSymmetricKey> type() {
    return TransparentSymmetricKey.class;
  }

  @Override
  public TransparentSymmetricKey createDefault() {
    Key key = Key.of(new byte[] {0x01, 0x02, 0x03});
    return TransparentSymmetricKey.of(key);
  }

  @Override
  public TransparentSymmetricKey createVariant() {
    Key key = Key.of(new byte[] {0x04, 0x05, 0x06});
    return TransparentSymmetricKey.of(key);
  }
}
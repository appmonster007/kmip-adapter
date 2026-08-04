package org.purplebean.kmip.codec.json.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.type.SaltLength;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("SaltLength JSON Serialization")
class SaltLengthJsonTest extends AbstractJsonSerializationTestSuite<SaltLength> {
  @Override
  public Class<SaltLength> type() {
    return SaltLength.class;
  }

  @Override
  public SaltLength createDefault() {
    return SaltLength.of(123);
  }

  @Override
  public SaltLength createVariant() {
    return SaltLength.of(456);
  }
}

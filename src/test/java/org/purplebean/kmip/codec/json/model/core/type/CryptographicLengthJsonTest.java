package org.purplebean.kmip.codec.json.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.type.CryptographicLength;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("CryptographicLength JSON Serialization Tests")
class CryptographicLengthJsonTest extends AbstractJsonSerializationTestSuite<CryptographicLength> {

  @Override
  public Class<CryptographicLength> type() {
    return CryptographicLength.class;
  }

  @Override
  public CryptographicLength createDefault() {
    return CryptographicLength.of(256);
  }

  @Override
  public CryptographicLength createVariant() {
    return CryptographicLength.of(512);
  }
}

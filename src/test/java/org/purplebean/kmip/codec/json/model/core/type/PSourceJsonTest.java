package org.purplebean.kmip.codec.json.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.type.PSource;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("PSource JSON Serialization")
class PSourceJsonTest extends AbstractJsonSerializationTestSuite<PSource> {
  @Override
  public Class<PSource> type() {
    return PSource.class;
  }

  @Override
  public PSource createDefault() {
    return PSource.of(new byte[] {0x01, 0x02, 0x03});
  }

  @Override
  public PSource createVariant() {
    return PSource.of(new byte[] {0x04, 0x05, 0x06});
  }
}

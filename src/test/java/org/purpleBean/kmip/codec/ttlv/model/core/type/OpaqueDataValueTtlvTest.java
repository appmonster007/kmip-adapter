package org.purplebean.kmip.codec.ttlv.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.type.OpaqueDataValue;
import org.purplebean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("OpaqueDataValue TTLV Serialization Tests")
class OpaqueDataValueTtlvTest extends AbstractTtlvSerializationTestSuite<OpaqueDataValue> {

  @Override
  public Class<OpaqueDataValue> type() {
    return OpaqueDataValue.class;
  }

  @Override
  public OpaqueDataValue createDefault() {
    return OpaqueDataValue.of(new byte[] {0x01, 0x02, 0x03});
  }

  @Override
  public OpaqueDataValue createVariant() {
    return OpaqueDataValue.of(new byte[] {0x04, 0x05, 0x06});
  }
}
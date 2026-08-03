package org.purpleBean.kmip.codec.json.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.OpaqueDataValue;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("OpaqueDataValue JSON Serialization Tests")
class OpaqueDataValueJsonTest extends AbstractJsonSerializationTestSuite<OpaqueDataValue> {

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
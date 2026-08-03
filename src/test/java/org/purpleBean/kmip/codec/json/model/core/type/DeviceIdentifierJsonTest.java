package org.purpleBean.kmip.codec.json.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.DeviceIdentifier;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("DeviceIdentifier JSON Serialization Tests")
class DeviceIdentifierJsonTest extends AbstractJsonSerializationTestSuite<DeviceIdentifier> {

  @Override
  public Class<DeviceIdentifier> type() {
    return DeviceIdentifier.class;
  }

  @Override
  public DeviceIdentifier createDefault() {
    return DeviceIdentifier
        .builder()
        .value("test-device-id")
        .build();
  }

  @Override
  public DeviceIdentifier createVariant() {
    return DeviceIdentifier
        .builder()
        .value("another-device-id")
        .build();
  }
}
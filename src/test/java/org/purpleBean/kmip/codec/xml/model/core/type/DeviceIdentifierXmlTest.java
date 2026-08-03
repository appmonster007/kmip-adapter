package org.purplebean.kmip.codec.xml.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.type.DeviceIdentifier;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("DeviceIdentifier XML Serialization Tests")
class DeviceIdentifierXmlTest extends AbstractXmlSerializationTestSuite<DeviceIdentifier> {

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